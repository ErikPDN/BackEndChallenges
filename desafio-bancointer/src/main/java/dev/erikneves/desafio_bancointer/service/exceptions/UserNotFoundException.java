package dev.erikneves.desafio_bancointer.service.exceptions;

public class UserNotFoundException extends RuntimeException {
  private static final long serialVerisonUID = 1L;

  public UserNotFoundException(String message) {
    super(message);
  }
}
