package sentinel_server_monitoring.repository;

import sentinel_server_monitoring.entity.Heartbeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeartbeatRepository extends JpaRepository<Heartbeat, Long> {
}