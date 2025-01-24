package dev.erik.userms.services.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
  public EmailAlreadyExistsException(String email) {
    super("Email already exists: " + email);
  }
}
