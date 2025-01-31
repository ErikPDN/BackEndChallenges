package dev.erik.twitter_simplificado.services.exceptions;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String message) {
    super(message);
  }

}
