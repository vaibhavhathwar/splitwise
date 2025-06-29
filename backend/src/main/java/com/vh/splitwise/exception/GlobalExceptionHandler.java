package com.vh.splitwise.exception;

import com.vh.splitwise.exception.authException.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(InvalidCredentialsException ex) {
        log.info("****Bad credentials for the email - {}****", ex.getEmail());
        return new ResponseEntity<>(new ErrorResponse("Unauthorized", ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        log.info("****User with email {} already exists****", ex.getEmail());
        return new ResponseEntity<>(new ErrorResponse("Conflict", ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorResponse> handlePasswordMismatch(PasswordMismatchException ex) {
        log.info("****Passwords do not match for the email while signup - {}****", ex.getEmail());
        return new ResponseEntity<>(new ErrorResponse("Invalid Request", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmailNotFound(EmailNotFoundException ex) {
        log.info("****User with email {} does not exist****", ex.getEmail());
        return new ResponseEntity<>(new ErrorResponse("Not Found", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OtpException.class)
    public ResponseEntity<ErrorResponse> handleOtpExceptions(OtpException ex) {
        log.info("****Otp exception for email - {} | {}****", ex.getEmail(), ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse("OTP Error", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordResetException.class)
    public ResponseEntity<ErrorResponse> handlePasswordReset(PasswordResetException ex) {
        log.info("****Password exception for email - {} | {}****", ex.getEmail(), ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse("Password Reset Error", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LogoutException.class)
    public ResponseEntity<ErrorResponse> handleLogoutException(LogoutException ex) {
        log.info("****User already logged out****");
        return new ResponseEntity<>(new ErrorResponse("Logout Error", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailDeliveryException.class)
    public ResponseEntity<ErrorResponse> handleEmailDelivery(EmailDeliveryException ex) {
        log.warn("Error occurred while sending email to {} | ", ex.getEmail(), ex.getCause());
        return new ResponseEntity<>(new ErrorResponse("Email sending error", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        log.warn("****Internal server error****", ex);
        ex.printStackTrace();
        ErrorResponse error = new ErrorResponse(
                "Internal Server Error",
                "Something went wrong. Please try again later.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
