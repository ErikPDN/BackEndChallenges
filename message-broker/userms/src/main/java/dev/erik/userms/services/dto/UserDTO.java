package dev.erik.userms.services.dto;

import java.util.UUID;

public record UserDTO(UUID userId, String name, String email) {
}
