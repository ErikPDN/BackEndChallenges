package dev.erik.twitter_simplificado.services;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public interface TweetService {
  void createTweet(String content, JwtAuthenticationToken token);

  void deleteTweet(Long id, JwtAuthenticationToken token);
}
