package controller.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserDto(
                      String login,

                      String password,

                      List<String> roles) {
}
