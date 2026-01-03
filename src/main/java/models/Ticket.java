package models;

import jakarta.persistence.*;
import lombok.Data;
import models.enums.ElevatorFloor;
import models.enums.Sector;
import models.enums.TicketPriority;
import models.enums.TicketStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ticket")
@Data
public class Ticket {

    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false)
    private UUID id;


    @Column(name = "message", length = 100, nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TicketStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketPriority priority;

    @Column(name = "openedAt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime openedAt;

    @Column(name = "closedAt", nullable = false)
    private LocalDateTime closedAt;


    @Enumerated(EnumType.STRING)
    @Column(name = "elevatorFloor", length = 50, nullable = false)
    private ElevatorFloor elevatorFloor;

    @Enumerated(EnumType.STRING)
    @Column(name = "sector", length = 50, nullable = false)
    private Sector sector;

}
