package com.vh.splitwise.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vh.splitwise.entity.Otp;

public interface OtpRepository extends JpaRepository<Otp, Integer> {
  public Optional<Otp> deleteByEmail(String email);

}
