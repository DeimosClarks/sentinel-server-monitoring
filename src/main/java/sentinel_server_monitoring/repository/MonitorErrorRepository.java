package sentinel_server_monitoring.repository;

import sentinel_server_monitoring.entity.MonitorError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorErrorRepository extends JpaRepository<MonitorError, Long> {
}