package com.vh.splitwise.service;

import com.vh.splitwise.exception.authException.EmailDeliveryException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
  private final JavaMailSender javaMailSender;

  @Value("${app.mail.from}")
  private String fromEmail;

  public EmailService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  public void sendOtp(String email, String otp) {
    try {
      SimpleMailMessage msg = new SimpleMailMessage();
      msg.setFrom(fromEmail);
      msg.setTo(email);
      msg.setSubject("OTP to reset your password");
      msg.setText("YOur OTP is: " + otp + "\n It expires in 5 minutes.");
      javaMailSender.send(msg);
    } catch (Exception e) {
      throw new EmailDeliveryException("Email sending failed", email, e);
    }
  }
}
