package dev.erik.twitter_simplificado.controllers;

import dev.erik.twitter_simplificado.controllers.dtos.CreateTweetRequestDTO;
import dev.erik.twitter_simplificado.services.TweetService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/tweets")
@RequiredArgsConstructor
@Slf4j
public class TweetController {
  private final TweetService tweetService;

  @PostMapping
  public ResponseEntity<Void> createTweet(@RequestBody CreateTweetRequestDTO tweetRequest,
      JwtAuthenticationToken token) {
    this.tweetService.createTweet(tweetRequest.content(), token);
    log.info("Tweet criado: {}", tweetRequest.content());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
