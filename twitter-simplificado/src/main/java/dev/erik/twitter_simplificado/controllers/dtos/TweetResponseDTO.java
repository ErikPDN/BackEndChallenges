package dev.erik.twitter_simplificado.controllers.dtos;

import java.time.Instant;

public record TweetResponseDTO(String content, String username, Instant createdAt) {
}
