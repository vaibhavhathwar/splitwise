package com.vh.splitwise.exception.authException;

public class PasswordResetException extends RuntimeException {
    private final String email;

    public PasswordResetException(String message, String email) {
        super(message);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
