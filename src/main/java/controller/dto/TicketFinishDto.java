package dto;

import jakarta.validation.constraints.NotNull;
import models.enums.TicketStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TicketFinishDto(
        @NotNull(message = "Id is required")
        UUID id,

        @NotNull(message = "Set the status")
        TicketStatus status
) {
}

