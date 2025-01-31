package dev.erik.twitter_simplificado.services.exceptions;

public class TweetNotFoundException extends RuntimeException {
  public TweetNotFoundException(String message) {
    super(message);
  }
}
