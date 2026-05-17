<template>
  <div class="min-h-screen bg-gray-900 text-white p-8">
    <div class="max-w-6xl mx-auto">
      <h1 class="text-4xl font-bold mb-8 text-center">Sentinel — Server Monitoring</h1>
      
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        <div v-for="server in servers" :key="server.id"
             class="bg-gray-800 rounded-xl p-6 border border-gray-700 hover:border-blue-500 transition-colors">
          <div class="flex justify-between items-start">
            <div>
              <h2 class="text-xl font-semibold">{{ server.name }}</h2>
              <p class="text-gray-400 text-sm">{{ server.ipAddress }}</p>
            </div>
            <span :class="statusBadge(server.status)"
                  class="px-3 py-1 rounded-full text-xs font-medium">
              {{ server.status }}
            </span>
          </div>
          
          <div class="mt-6 text-sm text-gray-400">
            Last heartbeat: 
            <span class="text-gray-300">{{ server.lastHeartbeat || '—' }}</span>
          </div>
        </div>
      </div>

      <div v-if="loading" class="text-center mt-8 text-gray-400">
        Loading servers...
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const servers = ref([])
const loading = ref(true)

const statusBadge = (status) => {
  if (status === 'ONLINE') return 'bg-green-500 text-white'
  if (status === 'WARNING') return 'bg-yellow-500 text-black'
  return 'bg-red-500 text-white'
}

onMounted(async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/servers')
    servers.value = response.data
  } catch (error) {
    console.error('Failed to fetch servers', error)
  } finally {
    loading.value = false
  }
})
</script>