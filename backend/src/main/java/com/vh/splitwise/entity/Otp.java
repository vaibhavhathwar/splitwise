package com.vh.splitwise.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "otps")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Otp {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String email;
  private String otp;
  private LocalDateTime createdAt;
  private LocalDateTime expiresAt;

  @PrePersist
  public void prePersist() {
    createdAt = LocalDateTime.now();
    expiresAt = createdAt.plusMinutes(5);
  }
}
