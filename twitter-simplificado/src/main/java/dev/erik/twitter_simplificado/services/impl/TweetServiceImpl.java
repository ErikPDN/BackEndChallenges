package dev.erik.twitter_simplificado.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import dev.erik.twitter_simplificado.controllers.dtos.TweetResponseDTO;
import dev.erik.twitter_simplificado.models.Tweet;
import dev.erik.twitter_simplificado.repositories.TweetRepository;
import dev.erik.twitter_simplificado.repositories.UserRepository;
import dev.erik.twitter_simplificado.services.TweetService;
import dev.erik.twitter_simplificado.services.exceptions.UserNotFoundException;
import dev.erik.twitter_simplificado.services.exceptions.TweetNotFoundException;
import dev.erik.twitter_simplificado.services.exceptions.UnauthorizedException;
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

  @Override
  public void deleteTweet(Long id, JwtAuthenticationToken token) {
    var user = this.userRepository.findById(UUID.fromString(token.getName()))
        .orElseThrow(() -> new UserNotFoundException("User not found"));

    var tweet = this.tweetRepository.findById(id)
        .orElseThrow(() -> new TweetNotFoundException("Tweet not found"));

    boolean isOwner = tweet.getUser().getUserId().equals(user.getUserId());
    boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN"));

    if (!isOwner && !isAdmin) { // Negamos a exclusão apenas se o usuário não for dono e não for admin
      throw new UnauthorizedException("You can only delete your own tweets");
    }

    this.tweetRepository.deleteById(id);
  }

  @Override
  public List<TweetResponseDTO> getTweets(JwtAuthenticationToken token) {
    var tweets = this.tweetRepository.findAll();
    var tweetsDTO = tweets.stream()
        .map(tweet -> new TweetResponseDTO(tweet.getContent(), tweet.getUser().getUsername(), tweet.getCreatedAt()))
        .collect(Collectors.toList());

    return tweetsDTO;
  }

}
