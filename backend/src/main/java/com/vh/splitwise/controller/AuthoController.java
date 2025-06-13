package com.vh.splitwise.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vh.splitwise.DTO.CheckEmailReq;
import com.vh.splitwise.DTO.CheckEmailRes;
import com.vh.splitwise.DTO.LoginRequest;
import com.vh.splitwise.DTO.LoginResponse;
import com.vh.splitwise.DTO.SignupRequest;
import com.vh.splitwise.DTO.SignupResponse;
import com.vh.splitwise.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthoController {
  private UserService userService;

  public AuthoController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("login")
  public LoginResponse loginController(@RequestBody LoginRequest loginRequest) {
    return userService.logIn(loginRequest);
  }

  @PostMapping("signup")
  public SignupResponse signupController(@RequestBody SignupRequest signupRequest) {
    return userService.signUp(signupRequest);
  }

  @PostMapping("checkEmail")
  public CheckEmailRes checkEmailController(@RequestBody CheckEmailReq checkEmailReq) {
    return userService.checkEmail(checkEmailReq);
  }
}
