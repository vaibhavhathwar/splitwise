package com.vh.splitwise.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vh.splitwise.DTO.AuthDTO.CheckEmailReq;
import com.vh.splitwise.DTO.AuthDTO.CheckEmailRes;
import com.vh.splitwise.DTO.AuthDTO.LoginRequest;
import com.vh.splitwise.DTO.AuthDTO.LoginResponse;
import com.vh.splitwise.DTO.AuthDTO.SignupRequest;
import com.vh.splitwise.DTO.AuthDTO.SignupResponse;
import com.vh.splitwise.DTO.AuthDTO.UpdatePasswordReq;
import com.vh.splitwise.DTO.AuthDTO.UpdatePasswordRes;
import com.vh.splitwise.DTO.AuthDTO.VerifyOtpReq;
import com.vh.splitwise.DTO.AuthDTO.VerifyOtpRes;
import com.vh.splitwise.entity.Otp;
import com.vh.splitwise.entity.PasswordResetToken;
import com.vh.splitwise.entity.User;
import com.vh.splitwise.mapper.UserMapper;
import com.vh.splitwise.repository.OtpRepository;
import com.vh.splitwise.repository.PasswordResetTokenRepo;
import com.vh.splitwise.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final OtpRepository otpRepository;
  private final EmailService emailService;
  private final PasswordResetTokenRepo passwordResetTokenRepo;
  private final UserMapper userMapper;

  public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository,
      OtpRepository otpRepository, EmailService emailService, PasswordResetTokenRepo passwordResetTokenRepo,
      UserMapper userMapper) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
    this.otpRepository = otpRepository;
    this.emailService = emailService;
    this.passwordResetTokenRepo = passwordResetTokenRepo;
    this.userMapper = userMapper;
  }

  public SignupResponse signUp(SignupRequest signupRequest) {
    if (!signupRequest.getPassword().equals(signupRequest.getRepeatPassword())) {
      return new SignupResponse(false, "Password and repeat password should match");
    }
    if (userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
      return new SignupResponse(false, "Email is already present");
    }
    User newUser = userMapper.toEntity(signupRequest);
    userRepository.save(newUser);
    return new SignupResponse(true, "Signup successfull");
  }

  public LoginResponse logIn(LoginRequest loginRequest) {
    Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
    if (user.isEmpty())
      return new LoginResponse(false, "Please provide a valid email");
    if (!passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
      return new LoginResponse(false, "Please enter the correct password");
    } else {
      return new LoginResponse(true, "Login successful");
    }
  }

  @Transactional
  public CheckEmailRes checkEmail(CheckEmailReq checkEmailReq) {
    Optional<User> user = userRepository.findByEmail(checkEmailReq.getEmail());
    if (user.isEmpty())
      return new CheckEmailRes("Please enter valid email address", false);
    String email = user.get().getEmail();
    String otp = generateOtp();
    Otp newOtp = new Otp();
    newOtp.setEmail(email);
    newOtp.setOtp(otp);
    otpRepository.deleteByEmail(email);
    Otp setOtp = otpRepository.save(newOtp);
    emailService.sendOtp(email, otp);
    return new CheckEmailRes("Please enter the otp sent to your regestered email", true);
  }

  @Transactional
  public VerifyOtpRes verifyOtp(VerifyOtpReq verifyOtpReq) {
    String email = verifyOtpReq.getEmail();
    Optional<Otp> dbOtp = otpRepository.findByEmail(email);
    if (dbOtp.isEmpty())
      return new VerifyOtpRes("Ooops..! Something went wrong. Try again!", "", false);
    LocalDateTime expiresAt = dbOtp.get().getExpiresAt();
    LocalDateTime currTime = LocalDateTime.now();
    if (currTime.isAfter(expiresAt))
      return new VerifyOtpRes("OTP expired. Try again!", "", false);
    if (!verifyOtpReq.getOtp().equals(dbOtp.get().getOtp()))
      return new VerifyOtpRes("Wrong otp. Try again!", "", false);
    String uuid = generateUUID();
    PasswordResetToken passToken = new PasswordResetToken();
    passToken.setEmail(email);
    passToken.setToken(uuid);
    passwordResetTokenRepo.deleteByEmail(email);
    passwordResetTokenRepo.save(passToken);
    otpRepository.deleteByEmail(email);
    return new VerifyOtpRes("OTP verified. Reset your password", uuid, true);
  }

  @Transactional
  public UpdatePasswordRes updatePassword(UpdatePasswordReq updatePasswordReq) {
    String email = updatePasswordReq.getEmail();
    Optional<PasswordResetToken> optionalDbToken = passwordResetTokenRepo.findByEmail(email);
    if (optionalDbToken.isEmpty())
      return new UpdatePasswordRes("Ooops..! Something went wrong. Try again!", false);
    PasswordResetToken passwordResetToken = optionalDbToken.get();
    LocalDateTime currTime = LocalDateTime.now();
    LocalDateTime tokenTime = passwordResetToken.getExpiresAt();
    if (currTime.isAfter(tokenTime))
      return new UpdatePasswordRes("Time out. Try again", false);
    String reqToken = updatePasswordReq.getToken();
    String dbToken = passwordResetToken.getToken();
    if (!reqToken.equals(dbToken))
      return new UpdatePasswordRes("Unauthorized action.", false);
    String password = updatePasswordReq.getPassword();
    String repeatPassword = updatePasswordReq.getRepeatPassword();
    if (!password.equals(repeatPassword))
      return new UpdatePasswordRes("Password does not match", false);
    Optional<User> optionalUser = userRepository.findByEmail(email);
    if (optionalUser.isEmpty())
      return new UpdatePasswordRes("Ooops..! User not found", false);
    User user = optionalUser.get();
    user.setPassword(passwordEncoder.encode(password));
    userRepository.save(user);
    passwordResetTokenRepo.delete(passwordResetToken);
    return new UpdatePasswordRes("Password reset.", true);
  }

  private String generateOtp() {
    SecureRandom sr = new SecureRandom();
    int otp = 100000 + sr.nextInt(900000);
    return String.valueOf(otp);
  }

  private String generateUUID() {
    return UUID.randomUUID().toString();
  }
}
