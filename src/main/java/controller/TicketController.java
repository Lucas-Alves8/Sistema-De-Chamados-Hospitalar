package controller;

import controller.dto.TicketCreateDto;
import controller.dto.TicketFinishDto;
import controller.dto.UpdateTicketDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import models.Ticket;
import org.springframework.web.bind.annotation.*;
import service.TicketService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TicketController {

    private final TicketService service;


    @PostMapping("/ticket")
    public Ticket createTicket(@RequestBody @Valid TicketCreateDto ticketDto) {

        return service.createTicket(
                ticketDto.message(),
                ticketDto.priority(),
                ticketDto.elevatorFloor(),
                ticketDto.sector()
        );
    }

    @PostMapping("/ticket/finish")
    public Ticket finishTicket(@RequestBody @Valid TicketFinishDto ticketFinishDto) {
        return service.finishTicket(
                ticketFinishDto.id(),
                ticketFinishDto.status()
        );
    }

    @PutMapping("/ticket/update")
    public Ticket updateTicket(@RequestBody @Valid UpdateTicketDto updateTicketDto) {

        return service.updateTicket(
                updateTicketDto.id(),
                updateTicketDto.priority(),
                updateTicketDto.status(),
                updateTicketDto.elevatorFloor(),
                updateTicketDto.message(),
                updateTicketDto.sector()
        );
    }
    @GetMapping("/ticket")
    public List<Ticket> listAllTickets() {
        return service.listAllTickets();
    }

    @GetMapping("/ticket/{id}")
    public Ticket getTicketById(@PathVariable UUID id) {
        return service.getTicketById(id);

    }


}

