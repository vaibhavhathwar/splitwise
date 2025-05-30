package com.vh.splitwise.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

  public boolean signUp(User user) {
    String password = user.getPassword();
    String encodedPassword = passwordEncoder.encode(password);
    user.setPassword(encodedPassword);
    userRepository.save(user);
    return true;
  }

  public boolean logIn(String email, String password) {
    User user = null;
    Optional<User> optionalUser = userRepository.findByEmail(email);
    if (optionalUser.isPresent())
      user = optionalUser.get();
    else
      return false;
    String encodedPasswrod = passwordEncoder.encode(password);
    if (passwordEncoder.matches(password, encodedPasswrod))
      return true;
    else
      return false;
  }
}
