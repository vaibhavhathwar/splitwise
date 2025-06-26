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
import org.springframework.security.core.context.SecurityContextHolder;
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
    LoginResponse res = userService.logIn(loginRequest, request);
    if (res.isSuccess())
      return ResponseEntity.ok(res);
    else
      return ResponseEntity.badRequest().body(res);
  }

  @PostMapping("signup")
  public ResponseEntity<SignupResponse> signupController(@RequestBody SignupRequest signupRequest) {
    SignupResponse res = userService.signUp(signupRequest);
    if (res.isSuccess())
      return ResponseEntity.ok(res);
    else
      return ResponseEntity.badRequest().body(res);
  }

  @PostMapping("checkemail")
  public ResponseEntity<CheckEmailRes> checkEmailController(@RequestBody CheckEmailReq checkEmailReq) {
    CheckEmailRes res = userService.checkEmail(checkEmailReq);
    if (res.isOtpGenerated()) {
      return ResponseEntity.ok(res);
    } else {
      return ResponseEntity.badRequest().body(res);
    }
  }

  @PostMapping("verifyotp")
  public ResponseEntity<VerifyOtpRes> verifyOtpController(@RequestBody VerifyOtpReq verifyOtpReq) {
    VerifyOtpRes res = userService.verifyOtp(verifyOtpReq);
    if (res.isOtpGenerated()) {
      return ResponseEntity.ok(res);
    } else {
      return ResponseEntity.badRequest().body(res);
    }
  }

  @PostMapping("updatepassword")
  public ResponseEntity<UpdatePasswordRes> updatePasswordController(@RequestBody UpdatePasswordReq updatePasswordReq) {
    UpdatePasswordRes res = userService.updatePassword(updatePasswordReq);
    if (res.isPasswordUpdated()) {
      return ResponseEntity.ok(res);
    } else {
      return ResponseEntity.badRequest().body(res);
    }
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout(HttpServletRequest request) {
    request.getSession(false).invalidate();
    SecurityContextHolder.clearContext();
    return ResponseEntity.ok("Logged out successfully");
  }

}
