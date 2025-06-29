package com.vh.splitwise.exception.authException;

public class OtpException extends RuntimeException {
    private final String email;

    public OtpException(String message, String email) {
        super(message);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
