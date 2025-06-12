package com.vh.splitwise.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
  private String email;
  private String userName;
  private String password;
  private String repeatPassword;
}
