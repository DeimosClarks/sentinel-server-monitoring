<template>
  <div class="min-h-screen bg-gray-950 text-white">
    <!-- Header -->
    <header class="bg-gray-900 border-b border-gray-800 px-8 py-6">
      <div class="max-w-6xl mx-auto flex justify-between items-center">
        <div>
          <h1 class="text-4xl font-bold tracking-tight">Sentinel</h1>
          <p class="text-gray-400 text-sm">Server Monitoring Dashboard</p>
        </div>
        <div class="flex items-center gap-4">
          <span class="text-xs text-gray-400">Last updated: {{ lastUpdated }}</span>
          <button 
            @click="refreshData"
            class="flex items-center gap-2 bg-blue-600 hover:bg-blue-700 px-6 py-3 rounded-xl font-medium transition-colors">
            🔄 Refresh
          </button>
        </div>
      </div>
    </header>

    <div class="max-w-6xl mx-auto p-8">
      <div v-if="loading" class="flex justify-center items-center py-20">
        <div class="animate-spin rounded-full h-12 w-12 border-t-4 border-blue-500"></div>
      </div>

      <div v-else class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
        <div v-for="server in servers" :key="server.id"
             class="bg-gray-900 border border-gray-700 rounded-3xl p-8 hover:border-blue-500 transition-all duration-200">
          
          <div class="flex justify-between items-start mb-6">
            <div>
              <h2 class="text-2xl font-semibold">{{ server.name }}</h2>
              <p class="font-mono text-gray-400 mt-1">{{ server.ipAddress }}</p>
            </div>
            <span :class="getStatusClass(server.status)" 
                  class="px-5 py-2 rounded-2xl text-sm font-semibold uppercase tracking-widest">
              {{ server.status }}
            </span>
          </div>

          <div class="space-y-5">
            <!-- CPU -->
            <div>
              <div class="flex justify-between text-sm mb-1">
                <span class="text-gray-400">CPU</span>
                <span class="font-medium">{{ server.cpu || '42' }}%</span>
              </div>
              <div class="h-2 bg-gray-800 rounded-full overflow-hidden">
                <div class="h-full bg-emerald-500 rounded-full" :style="{ width: (server.cpu || 42) + '%' }"></div>
              </div>
            </div>

            <!-- Memory -->
            <div>
              <div class="flex justify-between text-sm mb-1">
                <span class="text-gray-400">Memory</span>
                <span class="font-medium">{{ server.memory || '68' }}%</span>
              </div>
              <div class="h-2 bg-gray-800 rounded-full overflow-hidden">
                <div class="h-full bg-blue-500 rounded-full" :style="{ width: (server.memory || 68) + '%' }"></div>
              </div>
            </div>

            <div class="pt-4 border-t border-gray-700 text-sm text-gray-400">
              Last heartbeat: 
              <span class="text-gray-200">{{ server.lastHeartbeat ? new Date(server.lastHeartbeat).toLocaleString('de-DE') : '—' }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const servers = ref([])
const loading = ref(true)
const lastUpdated = ref(new Date().toLocaleTimeString('de-DE'))

const getStatusClass = (status) => {
  if (status === 'ONLINE') return 'bg-emerald-500 text-white'
  if (status === 'WARNING') return 'bg-amber-500 text-black'
  return 'bg-red-500 text-white'
}

const fetchServers = async () => {
  loading.value = true
  try {
    const response = await axios.get('http://localhost:8080/api/servers')
    servers.value = response.data.map(s => ({
      ...s,
      cpu: Math.floor(Math.random() * 40) + 30,     // dummy values
      memory: Math.floor(Math.random() * 50) + 40
    }))
    lastUpdated.value = new Date().toLocaleTimeString('de-DE')
  } catch (error) {
    console.error('Fehler beim Laden:', error)
  } finally {
    loading.value = false
  }
}

const refreshData = () => fetchServers()

onMounted(fetchServers)
</script>