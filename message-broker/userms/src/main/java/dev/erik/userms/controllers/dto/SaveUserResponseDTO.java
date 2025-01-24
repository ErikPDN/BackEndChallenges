package dev.erik.userms.controllers.dto;

import java.util.UUID;

public record SaveUserResponseDTO(UUID userId, String name, String email) {
}
