package dev.erikneves.desafio_bancointer.service.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public EmailAlreadyExistsException() {
    super("Email already exists");
  }
}
