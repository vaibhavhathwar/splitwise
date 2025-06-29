package com.vh.splitwise.exception.authException;

public class EmailNotFoundException extends RuntimeException {
    private final String email;

    public EmailNotFoundException(String message, String email) {
        super(message);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
