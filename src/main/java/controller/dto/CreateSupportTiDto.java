package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import models.enums.ElevatorFloor;
import org.hibernate.validator.constraints.br.CPF;

public record CreateSupportTiDto(@NotNull(message = "I need to know your name")
                                 String name,

                                 @NotNull(message = "CPF is the most important")
                                 @CPF(message = "Invalid CPF")
                                 String cpf,

                                 @NotNull(message = "Elevator Floor is necessary")
                                 ElevatorFloor elevatorFloor,

                                 @NotBlank(message = "RG is required")
                                 @Pattern(regexp = "^[0-9Xx.-]{5,14}$", message = "RG invalid")
                                 String rg,

                                 @NotNull(message = "Your age is important")
                                 Integer age) {
}
