package com.vh.splitwise.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vh.splitwise.DTO.AuthDTO.CheckEmailReq;
import com.vh.splitwise.DTO.AuthDTO.CheckEmailRes;
import com.vh.splitwise.DTO.AuthDTO.LoginRequest;
import com.vh.splitwise.DTO.AuthDTO.LoginResponse;
import com.vh.splitwise.DTO.AuthDTO.SignupRequest;
import com.vh.splitwise.DTO.AuthDTO.SignupResponse;
import com.vh.splitwise.DTO.AuthDTO.UpdatePasswordReq;
import com.vh.splitwise.DTO.AuthDTO.UpdatePasswordRes;
import com.vh.splitwise.DTO.AuthDTO.VerifyOtpReq;
import com.vh.splitwise.DTO.AuthDTO.VerifyOtpRes;
import com.vh.splitwise.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("login")
  public ResponseEntity<LoginResponse> loginController(@RequestBody LoginRequest loginRequest,
      HttpServletRequest request) {
    return ResponseEntity.ok(userService.logIn(loginRequest, request));
  }

  @PostMapping("signup")
  public SignupResponse signupController(@RequestBody SignupRequest signupRequest) {
    return userService.signUp(signupRequest);
  }

  @PostMapping("checkemail")
  public CheckEmailRes checkEmailController(@RequestBody CheckEmailReq checkEmailReq) {
    return userService.checkEmail(checkEmailReq);
  }

  @PostMapping("verifyotp")
  public VerifyOtpRes verifyOtpController(@RequestBody VerifyOtpReq verifyOtpReq) {
    return userService.verifyOtp(verifyOtpReq);
  }

  @PostMapping("updatepassword")
  public UpdatePasswordRes updatePasswordController(@RequestBody UpdatePasswordReq updatePasswordReq) {
    return userService.updatePassword(updatePasswordReq);
  }
}
