package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import models.enums.ElevatorFloor;
import models.enums.Sector;
import models.enums.TicketPriority;
import models.enums.TicketStatus;

public record TicketCreateDto(
        @NotBlank(message = "Describe your problem")
        String message,
        @NotNull(message = "What is the urgency for your ticket?")
        TicketPriority priority,
        @NotNull(message = "Status")
        TicketStatus status,
        @NotNull(message = "What is the floor where you are?")
        ElevatorFloor elevatorFloor,
        @NotNull(message = "What is your sector?")
        Sector sector
        ) {
        }



