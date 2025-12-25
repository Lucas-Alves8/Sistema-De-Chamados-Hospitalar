package dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserDto(
                      @NotNull(message = "he cant be null")
                      String login,

                      @NotNull(message = "password is necessary")
                      String password,

                      @NotNull(message = "role is so important")
                      List<String> roles) {
}
