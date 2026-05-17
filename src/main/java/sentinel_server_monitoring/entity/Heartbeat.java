package sentinel_server_monitoring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Heartbeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Server server;

    private LocalDateTime timestamp;

    private double cpuUsage;
    private double memoryUsage;
    private double diskSpaceLeft;
}