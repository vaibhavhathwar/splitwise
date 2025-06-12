package com.vh.splitwise.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vh.splitwise.DTO.LoginRequest;
import com.vh.splitwise.DTO.LoginResponse;
import com.vh.splitwise.DTO.SignupRequest;
import com.vh.splitwise.DTO.SignupResponse;
import com.vh.splitwise.entity.User;
import com.vh.splitwise.repository.UserRepository;

@Service
public class UserService {

  private PasswordEncoder passwordEncoder;
  private UserRepository userRepository;

  public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  public SignupResponse signUp(SignupRequest signupRequest) {
    Optional<User> user = userRepository.findByEmail(signupRequest.getEmail());
    if (!user.isEmpty()) {
      return new SignupResponse(false, "Email is already present");
    }
    if (!signupRequest.getPassword().equals(signupRequest.getRepeatPassword())) {
      return new SignupResponse(false, "Password and repeat password should match");
    }
    User newUser = new User();
    newUser.setEmail(signupRequest.getEmail());
    newUser.setUsername(signupRequest.getUserName());
    newUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
    User insertedUser = userRepository.save(newUser);
    return new SignupResponse(true, "Signup successfull");
  }

  public LoginResponse logIn(LoginRequest loginRequest) {
    Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
    if (user.isEmpty())
      return new LoginResponse(false, "Please provide a valid email");
    if (!passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
      return new LoginResponse(false, "Please enter the correct password");
    } else {
      return new LoginResponse(true, "Login successful");
    }
  }
}
