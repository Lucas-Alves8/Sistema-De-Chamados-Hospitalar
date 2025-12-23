package service;

import lombok.RequiredArgsConstructor;
import models.Ticket;
import models.enums.TicketPriority;
import models.enums.TicketStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import repository.TicketRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository repository;


    @PreAuthorize("hasAnyRole('EMPLOYEE, SUPPORTTI')")
    public Ticket createTicket(UUID uuid, String message, TicketPriority priority) {
        Ticket ticket = new Ticket();

        ticket.setMessage(message);
        ticket.setPriority(priority);
        ticket.setStatus(TicketStatus.OPEN);

        return repository.save(ticket);
    }

    @PreAuthorize("hasRole('SUPPORTTI')")
    public Ticket finishTicket(UUID id, TicketStatus status, LocalDateTime closedAt) {

        Ticket ticket = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticket.getStatus() != TicketStatus.OPEN) {
            throw new RuntimeException("ticket not open or not existed");
        }

        ticket.setStatus(status);
        ticket.setClosedAt(closedAt);

        return repository.save(ticket);

    }

    public Ticket checkTimeLimit(Ticket ticket){

        LocalDateTime now =LocalDateTime.now();

        long hours = Duration.between(ticket.getOpenedAt(), now).toHours();

        if (hours >= 15 && ticket.getStatus() == TicketStatus.OPEN){
            ticket.setPriority(TicketPriority.SOFT);
        }

        if (hours >= 24 && ticket.getStatus() == TicketStatus.OPEN){
            ticket.setPriority(TicketPriority.SOFT);
        }

    }
}
