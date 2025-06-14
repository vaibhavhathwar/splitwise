package com.vh.splitwise.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vh.splitwise.entity.PasswordResetToken;

public interface PasswordResetTokenRepo extends JpaRepository<PasswordResetToken, Integer> {
  public Optional<PasswordResetToken> findByEmail(String email);

  public Optional<PasswordResetToken> deleteByEmail(String email);
}
