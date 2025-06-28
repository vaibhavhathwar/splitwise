package com.vh.splitwise.exception;

import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vh.splitwise.exception.authException.EmailAlreadyExistsException;
import com.vh.splitwise.exception.authException.EmailNotFoundException;
import com.vh.splitwise.exception.authException.InvalidCredentialsException;
import com.vh.splitwise.exception.authException.LogoutException;
import com.vh.splitwise.exception.authException.OtpException;
import com.vh.splitwise.exception.authException.PasswordMismatchException;
import com.vh.splitwise.exception.authException.PasswordResetException;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleInvalidCredentials(InvalidCredentialsException ex) {
    return new ResponseEntity<>(new ErrorResponse("Unauthorized", ex.getMessage()), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
    return new ResponseEntity<>(new ErrorResponse("Conflict", ex.getMessage()), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(PasswordMismatchException.class)
  public ResponseEntity<ErrorResponse> handlePasswordMismatch(PasswordMismatchException ex) {
    return new ResponseEntity<>(new ErrorResponse("Invalid Request", ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EmailNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEmailNotFound(EmailNotFoundException ex) {
    return new ResponseEntity<>(new ErrorResponse("Not Found", ex.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(OtpException.class)
  public ResponseEntity<ErrorResponse> handleOtpExceptions(OtpException ex) {
    return new ResponseEntity<>(new ErrorResponse("OTP Error", ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(PasswordResetException.class)
  public ResponseEntity<ErrorResponse> handlePasswordReset(PasswordResetException ex) {
    return new ResponseEntity<>(new ErrorResponse("Password Reset Error", ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(LogoutException.class)
  public ResponseEntity<ErrorResponse> handleLogoutException(LogoutException ex) {
    return new ResponseEntity<>(new ErrorResponse("Logout Error", ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
    ex.printStackTrace();
    ErrorResponse error = new ErrorResponse(
        "Internal Server Error",
        "Something went wrong. Please try again later.");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }

}
