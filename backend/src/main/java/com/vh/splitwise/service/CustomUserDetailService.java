package com.vh.splitwise.service;

import java.util.List;

import com.vh.splitwise.exception.authException.EmailNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vh.splitwise.entity.User;
import com.vh.splitwise.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
  private final UserRepository userRepository;

  public CustomUserDetailService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new EmailNotFoundException("Email not found while creating User", email));
    return new org.springframework.security.core.userdetails.User(
        user.getEmail(),
        user.getPassword(),
        List.of(new SimpleGrantedAuthority("ROLE_USER")));
  }
}
