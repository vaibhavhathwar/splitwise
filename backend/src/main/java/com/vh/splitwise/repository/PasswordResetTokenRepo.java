package com.vh.splitwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vh.splitwise.entity.PasswordResetToken;

public interface PasswordResetTokenRepo extends JpaRepository<PasswordResetToken, Integer> {

}
