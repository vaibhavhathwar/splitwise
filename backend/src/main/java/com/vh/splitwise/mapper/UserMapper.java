package com.vh.splitwise.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.vh.splitwise.DTO.AuthDTO.SignupRequest;
import com.vh.splitwise.entity.User;

@Component
public class UserMapper {
  private final PasswordEncoder passwordEncoder;

  public UserMapper(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public User toEntity(SignupRequest signupRequest) {
    return User.builder()
        .username(signupRequest.getUsername())
        .email(signupRequest.getEmail())
        .password(passwordEncoder.encode(signupRequest.getPassword()))
        .build();
  }
}
