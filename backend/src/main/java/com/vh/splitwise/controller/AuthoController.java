package com.vh.splitwise.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vh.splitwise.entity.User;
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
  public boolean loginController(@RequestBody String email, @RequestBody String password) {
    if (userService.logIn(email, password))
      return true;
    else
      return false;
  }

  @PostMapping("signup")
  public boolean signupController(@RequestBody User user) {
    userService.signUp(user);
    return true;
  }

}
