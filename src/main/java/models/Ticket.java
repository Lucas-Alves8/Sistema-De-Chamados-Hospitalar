package models;

import jakarta.persistence.*;
import lombok.Data;
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
    @GeneratedValue
    @UuidGenerator
    @Column(nullable = false, updatable = false)
    private UUID id;


    @Column(name = "message", length = 100, nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketPriority priority;

    @Column(name = "openedAt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime openedAt;

    @Column(name = "closedAt", nullable = false)
    private LocalDateTime closedAt;

    @Column(name = "dateLimit", nullable = false, updatable = false)
    private LocalDateTime dateLimit;

}
