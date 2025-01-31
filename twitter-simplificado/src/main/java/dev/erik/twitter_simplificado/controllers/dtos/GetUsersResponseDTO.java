package dev.erik.twitter_simplificado.controllers.dtos;

import java.util.UUID;
import java.util.List;
import dev.erik.twitter_simplificado.controllers.dtos.UserResponseDTO;

public record GetUsersResponseDTO(List<UserResponseDTO> users) {
}
