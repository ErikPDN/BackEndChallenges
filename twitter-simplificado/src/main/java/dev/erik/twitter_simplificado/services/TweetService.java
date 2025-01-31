package dev.erik.twitter_simplificado.services;

import java.util.List;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import dev.erik.twitter_simplificado.controllers.dtos.TweetResponseDTO;

public interface TweetService {
  void createTweet(String content, JwtAuthenticationToken token);

  void deleteTweet(Long id, JwtAuthenticationToken token);

  List<TweetResponseDTO> getTweets(JwtAuthenticationToken token);
}
