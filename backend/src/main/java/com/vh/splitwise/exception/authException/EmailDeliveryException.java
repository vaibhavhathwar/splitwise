package com.vh.splitwise.exception.authException;

public class EmailDeliveryException extends RuntimeException {
    private final String email;

    public EmailDeliveryException(String message, String email, Throwable cause) {
        super(message, cause);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
