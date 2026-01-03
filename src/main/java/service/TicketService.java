package service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import models.Ticket;
import models.enums.ElevatorFloor;
import models.enums.Sector;
import models.enums.TicketPriority;
import models.enums.TicketStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import repository.TicketRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Data
public class TicketService {

    private final TicketRepository repository;


    //@PreAuthorize("hasAnyRole('EMPLOYEE', SUPPORTTI', 'ADMIN')")
    public Ticket createTicket(String message, TicketPriority priority, ElevatorFloor elevatorFloor, Sector sector) {
        Ticket ticket = new Ticket();

        ticket.setMessage(message);
        ticket.setPriority(priority);
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setOpenedAt(LocalDateTime.now());
        ticket.setElevatorFloor(elevatorFloor);
        ticket.setSector(sector);

        return repository.save(ticket);
    }

    @PreAuthorize("hasAnyRole('SUPPORTTI', 'ADMIN')")
    public Ticket finishTicket(UUID id, TicketStatus status) {

        Ticket ticket = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticket.getStatus() != TicketStatus.OPEN) {
            throw new RuntimeException("ticket not open or not existed");
        }

        ticket.setStatus(status);
        ticket.setClosedAt(LocalDateTime.now());

        return repository.save(ticket);

    }

    public Ticket timeLimit(Ticket ticket) {

        if (ticket.getStatus() != TicketStatus.OPEN) {
            return ticket;
        }

        LocalDateTime now = LocalDateTime.now();
        long hours = Duration.between(ticket.getOpenedAt(), now).toHours();

        if (hours >= 28) {
            ticket.setPriority(TicketPriority.HIGHURGENCY);
        } else if (hours >= 24) {
            ticket.setPriority(TicketPriority.LOWURGENCY);
        } else if (hours >= 20) {
            ticket.setPriority(TicketPriority.MEDIUM);
        } else if (hours >= 16) {
            ticket.setPriority(TicketPriority.SOFT);
        }
        return repository.save(ticket);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Ticket updateTicket(UUID id, TicketPriority priority, TicketStatus status, ElevatorFloor elevatorFloor, String message, Sector sector) {

        Ticket ticket = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found or not existed"));

        if (ticket.getStatus() == TicketStatus.OPEN) {

            ticket.setPriority(priority);
            ticket.setStatus(status);
            ticket.setElevatorFloor(elevatorFloor);
            ticket.setMessage(message);
            ticket.setSector(sector);

        }
        return repository.save(ticket);
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'SUPPORTTI', 'ADMIN')")
    public List<Ticket> listAllTickets() {

        List<Ticket> tickets = repository.findAll();
        tickets.forEach(this::timeLimit);

        return tickets.stream()
                .map(this::timeLimit)
                .toList();
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'SUPPORTTI', 'ADMIN')")
    public Ticket getTicketById(UUID id) {
        Ticket ticket = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id not found or not existed"));
        return timeLimit(ticket);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Ticket deleteTicket(UUID id) {

        Ticket ticket = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found or not existed"));

        repository.delete(ticket);
        return ticket;
    }

}
