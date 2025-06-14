package com.vh.splitwise.DTO.AuthDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordReq {
  private String email;
  private String password;
  private String repeatPassword;
  private String token;
}
