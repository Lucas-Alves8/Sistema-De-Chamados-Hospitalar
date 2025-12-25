package dto;

import jakarta.validation.constraints.NotNull;
import models.enums.ElevatorFloor;
import models.enums.Sector;
import models.enums.TicketPriority;
import models.enums.TicketStatus;

import java.util.UUID;

public record UpdateTicketDto(
        @NotNull(message = "Id is Necessary")
        UUID id,

        @NotNull(message = "What are the priority?")
        TicketPriority priority,

        @NotNull(message = "Status is need")
        TicketStatus status,

        @NotNull(message = "In what floor you are?")
        ElevatorFloor elevatorFloor,

        @NotNull(message = "Messsage is need")
        String message,

        @NotNull(message = "Need to be preenched")
        Sector sector

) {
}
