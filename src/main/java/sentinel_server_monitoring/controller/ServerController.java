package sentinel_server_monitoring.controller;

import sentinel_server_monitoring.entity.Server;
import sentinel_server_monitoring.repository.ServerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servers")
@CrossOrigin(origins = "*")   // for later Vue frontend
public class ServerController {

    private final ServerRepository serverRepository;

    public ServerController(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @GetMapping
    public List<Server> getAllServers() {
        return serverRepository.findAll();
    }

    @GetMapping("/{id}")
    public Server getServerById(@PathVariable Long id) {
        return serverRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Server createServer(@RequestBody Server server) {
        return serverRepository.save(server);
    }
}