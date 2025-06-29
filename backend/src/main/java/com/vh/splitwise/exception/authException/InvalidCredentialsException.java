package com.vh.splitwise.exception.authException;

public class InvalidCredentialsException extends RuntimeException {
    private final String email;

    public InvalidCredentialsException(String message, String email) {
        super(message);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
