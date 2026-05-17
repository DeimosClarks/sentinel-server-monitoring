package sentinel_server_monitoring;

import sentinel_server_monitoring.entity.Server;
import sentinel_server_monitoring.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ServerRepository serverRepository;

    public DataInitializer(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @Override
    public void run(String... args) {
        if (serverRepository.count() == 0) {
            Server s1 = new Server(null, "Web Server 01", "192.168.1.10", "ONLINE", null);
            Server s2 = new Server(null, "DB Server 02", "192.168.1.11", "ONLINE", null);
            Server s3 = new Server(null, "Auth Service 03", "192.168.1.12", "WARNING", null);

            serverRepository.saveAll(List.of(s1, s2, s3));
            System.out.println("✅ Sample servers initialized!");
        }
    }
}