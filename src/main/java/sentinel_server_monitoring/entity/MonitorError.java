package sentinel_server_monitoring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonitorError {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Server server;

    private LocalDateTime timestamp;
    private String source;
    private String details;
    private int severity; // 1 = low, 2 = medium, 3 = high
}