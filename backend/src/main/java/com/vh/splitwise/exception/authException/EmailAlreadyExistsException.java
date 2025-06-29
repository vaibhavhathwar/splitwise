package com.vh.splitwise.exception.authException;

public class EmailAlreadyExistsException extends RuntimeException {
  private final String email;
  public EmailAlreadyExistsException(String message, String email) {
    super(message);
    this.email = email;
  }
  public String getEmail() {
    return email;
  }
}
