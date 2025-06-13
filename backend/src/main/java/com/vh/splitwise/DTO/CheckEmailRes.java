package com.vh.splitwise.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckEmailRes {
  private String message;
  private boolean isOtpGenerated;
}
