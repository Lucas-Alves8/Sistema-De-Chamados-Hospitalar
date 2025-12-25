package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

public record SupportTiDto(
                           @NotNull(message = "id is the key")
                           UUID id,

                           @NotNull(message = "i need to know your name")
                           String name,

                           @CPF(message = "cpf invalid")
                           @NotNull(message = "cpf is the thing most important")
                           @UniqueElements(message = "your cpf is used")
                           String cpf,

                           @NotNull(message = "Your age is important")
                           int age,

                           @NotBlank(message = "RG is so much necessary")
                           @Pattern(
                                   regexp = "^[0-9Xx.-]{5,14}$",
                                   message = "RG invalid")
                           String rg) {
}
