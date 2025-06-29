package com.vh.splitwise.exception.authException;

public class PasswordMismatchException extends RuntimeException {
  private final String email;
  public PasswordMismatchException(String message, String email) {
    super(message);
    this.email = email;

  }
  public String getEmail() {
    return email;
  }
}
