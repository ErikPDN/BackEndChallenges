package dev.erik.twitter_simplificado.services.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

  public UserAlreadyExistsException(String message) {
    super(message);
  }

}
