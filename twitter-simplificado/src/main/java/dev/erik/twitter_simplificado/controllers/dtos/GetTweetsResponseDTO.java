package dev.erik.twitter_simplificado.controllers.dtos;

import java.util.List;

public record GetTweetsResponseDTO(List<TweetResponseDTO> tweets) {
}
