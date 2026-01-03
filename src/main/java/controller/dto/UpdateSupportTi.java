package controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

public record UpdateSupportTi(@NotNull(message = "Id is necessary")
                              UUID id,

                              @NotNull(message = "I need to know your name")
                              String name,

                              @NotNull(message = "CPF is the most important")
                              @CPF(message = "Invalid CPF")
                              String cpf,

                              @NotNull(message = "RG is required")
                              @Pattern(regexp = "^[0-9Xx.-]{5,14}$", message = "RG invalid")
                              String rg,

                              @NotNull(message = "Your age is important")
                              Integer age) {
}
