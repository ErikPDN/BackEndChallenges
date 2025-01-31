package dev.erik.twitter_simplificado.services.exceptions;

public class PasswordNotMatchException extends RuntimeException {

  public PasswordNotMatchException(String message) {
    super(message);
  }

}
