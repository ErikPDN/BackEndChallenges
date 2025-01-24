package dev.erik.userms.controllers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SaveUserRequestDTO(
    @NotBlank String name,
    @NotBlank @Email String email) {
}
