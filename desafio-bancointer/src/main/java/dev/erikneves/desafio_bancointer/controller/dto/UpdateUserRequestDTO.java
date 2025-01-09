package dev.erikneves.desafio_bancointer.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateUserRequestDTO(
    @Schema(description = "User name", example = "John Doe") String name,
    @Schema(description = "User email", example = "jhon.doe@gmail.com") String email) {
}
