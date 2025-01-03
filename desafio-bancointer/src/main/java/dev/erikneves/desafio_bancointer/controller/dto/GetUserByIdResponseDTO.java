package dev.erikneves.desafio_bancointer.controller.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetUserByIdResponseDTO(
    @Schema(description = "User Id", example = "a77d244e-7522-47bb-9430-ec14197fa3f3") UUID id,
    @Schema(description = "User name", example = "John Doe") String name,
    @Schema(description = "User email", example = "john.doe@gmail.com") String email) {
}
