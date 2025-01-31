package dev.erik.twitter_simplificado.services.impl;

import java.util.UUID;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import dev.erik.twitter_simplificado.models.Tweet;
import dev.erik.twitter_simplificado.repositories.TweetRepository;
import dev.erik.twitter_simplificado.repositories.UserRepository;
import dev.erik.twitter_simplificado.services.TweetService;
import dev.erik.twitter_simplificado.services.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {
  private final UserRepository userRepository;
  private final TweetRepository tweetRepository;

  @Override
  public void createTweet(String content, JwtAuthenticationToken token) {

    var user = this.userRepository.findById(UUID.fromString(token.getName()))
        .orElseThrow(() -> new UserNotFoundException("User not found"));

    var tweet = new Tweet();
    tweet.setContent(content);
    tweet.setUser(user);
    this.tweetRepository.save(tweet);
  }
}
