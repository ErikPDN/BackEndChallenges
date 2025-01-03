package dev.erikneves.desafio_bancointer.service.dto;

import java.util.UUID;

import lombok.Builder;

@Builder
public record UserDTO(UUID id, String name, String email) {
}
