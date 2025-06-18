package com.vh.splitwise.DTO.AuthDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
  private String email;
  private String username;
  private String password;
  private String repeatPassword;
}
