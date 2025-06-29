package com.vh.splitwise.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vh.splitwise.DTO.AuthDTO.CheckEmailReq;
import com.vh.splitwise.DTO.AuthDTO.CheckEmailRes;
import com.vh.splitwise.DTO.AuthDTO.LoginRequest;
import com.vh.splitwise.DTO.AuthDTO.LoginResponse;
import com.vh.splitwise.DTO.AuthDTO.LogoutRes;
import com.vh.splitwise.DTO.AuthDTO.SignupRequest;
import com.vh.splitwise.DTO.AuthDTO.SignupResponse;
import com.vh.splitwise.DTO.AuthDTO.UpdatePasswordReq;
import com.vh.splitwise.DTO.AuthDTO.UpdatePasswordRes;
import com.vh.splitwise.DTO.AuthDTO.VerifyOtpReq;
import com.vh.splitwise.DTO.AuthDTO.VerifyOtpRes;
import com.vh.splitwise.entity.Otp;
import com.vh.splitwise.entity.PasswordResetToken;
import com.vh.splitwise.entity.User;
import com.vh.splitwise.exception.authException.EmailAlreadyExistsException;
import com.vh.splitwise.exception.authException.EmailNotFoundException;
import com.vh.splitwise.exception.authException.InvalidCredentialsException;
import com.vh.splitwise.exception.authException.LogoutException;
import com.vh.splitwise.exception.authException.OtpException;
import com.vh.splitwise.exception.authException.PasswordMismatchException;
import com.vh.splitwise.exception.authException.PasswordResetException;
import com.vh.splitwise.mapper.UserMapper;
import com.vh.splitwise.repository.OtpRepository;
import com.vh.splitwise.repository.PasswordResetTokenRepo;
import com.vh.splitwise.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
@Slf4j
public class UserService {
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final OtpRepository otpRepository;
  private final EmailService emailService;
  private final PasswordResetTokenRepo passwordResetTokenRepo;
  private final UserMapper userMapper;
  private final AuthenticationManager authenticationManager;

  public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository,
      OtpRepository otpRepository, EmailService emailService, PasswordResetTokenRepo passwordResetTokenRepo,
      UserMapper userMapper, AuthenticationManager authenticationManager) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
    this.otpRepository = otpRepository;
    this.emailService = emailService;
    this.passwordResetTokenRepo = passwordResetTokenRepo;
    this.userMapper = userMapper;
    this.authenticationManager = authenticationManager;
  }

  public SignupResponse signUp(SignupRequest signupRequest) {
    String email = signupRequest.getEmail();
    log.info("****Signup request received for the email - {}****", email);
    if (!signupRequest.getPassword().equals(signupRequest.getRepeatPassword())) {
      throw new PasswordMismatchException("Password and repeat password should match", email);
    }
    if (userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
      throw new EmailAlreadyExistsException("Email is already present", email);
    }
    User newUser = userMapper.toEntity(signupRequest);
    userRepository.save(newUser);
    log.info("****Signup successful for the email - {}****", email);
    return new SignupResponse(true, "Signup successful");
  }

  public LoginResponse logIn(LoginRequest loginRequest, HttpServletRequest request) {
    String email = loginRequest.getEmail();
    log.info("****Login request received for the email - {}****", email);
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginRequest.getEmail(),
              loginRequest.getPassword()));
      SecurityContext context = SecurityContextHolder.createEmptyContext();
      context.setAuthentication(authentication);
      SecurityContextHolder.setContext(context);

      HttpSession session = request.getSession(true);
      session.setAttribute("SPRING_SECURITY_CONTEXT", context);
      log.info("****Login successful for the email - {}****", email);
      return new LoginResponse(true, "Login successful");
    } catch (BadCredentialsException ex) {
      throw new InvalidCredentialsException("Invalid email or password", email);
    }
  }

  @Transactional
  public CheckEmailRes checkEmail(CheckEmailReq checkEmailReq) {
    String email = checkEmailReq.getEmail();
    Optional<User> user = userRepository.findByEmail(email);
    if (user.isEmpty()) {
      throw new EmailNotFoundException("Please enter valid email address", email);
    }
    otpRepository.deleteByEmail(email);
    String otp = generateOtp();
    Otp newOtp = new Otp();
    newOtp.setEmail(email);
    newOtp.setOtp(otp);
    otpRepository.save(newOtp);
    emailService.sendOtp(email, otp);
    log.info("****OTP sent for the email - {}****", email);
    return new CheckEmailRes("Please enter the otp sent to your registered email", true);
  }

  @Transactional
  public VerifyOtpRes verifyOtp(VerifyOtpReq verifyOtpReq) {
    String email = verifyOtpReq.getEmail();
    String inputOtp = verifyOtpReq.getOtp();
    Optional<Otp> dbOtp = otpRepository.findByEmail(email);
    if (dbOtp.isEmpty()) {
      throw new OtpException("Ooops..! Something went wrong. Try again!", email);
    }
    LocalDateTime expiresAt = dbOtp.get().getExpiresAt();
    LocalDateTime currTime = LocalDateTime.now();
    if (currTime.isAfter(expiresAt)) {
      otpRepository.deleteByEmail(email);
      throw new OtpException("OTP expired. Try again!" , email);
    }
    if (!inputOtp.equals(dbOtp.get().getOtp())) {
      throw new OtpException("Wrong otp. Try again!", email);
    }
    String uuid = generateUUID();
    PasswordResetToken passToken = new PasswordResetToken();
    passToken.setEmail(email);
    passToken.setToken(uuid);
    passwordResetTokenRepo.deleteByEmail(email);
    passwordResetTokenRepo.save(passToken);
    log.info("****Otp varified for the email- {}****", email);
    return new VerifyOtpRes("OTP verified. Reset your password", uuid, true);
  }

  @Transactional
  public UpdatePasswordRes updatePassword(UpdatePasswordReq updatePasswordReq) {
    String email = updatePasswordReq.getEmail();
    Optional<PasswordResetToken> optionalDbToken = passwordResetTokenRepo.findByEmail(email);
    if (optionalDbToken.isEmpty()) {
      throw new PasswordResetException("Ooops..! Something went wrong. Try again!", email);
    }
    PasswordResetToken passwordResetToken = optionalDbToken.get();
    LocalDateTime currTime = LocalDateTime.now();
    LocalDateTime tokenTime = passwordResetToken.getExpiresAt();
    if (currTime.isAfter(tokenTime)) {
      throw new PasswordResetException("Time out. Try again", email);
    }
    String reqToken = updatePasswordReq.getToken();
    String dbToken = passwordResetToken.getToken();
    if (!reqToken.equals(dbToken)) {
      throw new PasswordResetException("Unauthorized action.", email);
    }
    String password = updatePasswordReq.getPassword();
    String repeatPassword = updatePasswordReq.getRepeatPassword();
    if (!password.equals(repeatPassword)) {
      throw new PasswordResetException("Password does not match", email);
    }
    Optional<User> optionalUser = userRepository.findByEmail(email);
    if (optionalUser.isEmpty()) {
      throw new PasswordResetException("Ooops..! User not found", email);
    }
    User user = optionalUser.get();
    user.setPassword(passwordEncoder.encode(password));
    userRepository.save(user);
    passwordResetTokenRepo.delete(passwordResetToken);
    return new UpdatePasswordRes("Password reset.", true);
  }

  public LogoutRes logout(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session == null) {
      throw new LogoutException("Already logged out");
    }
    session.invalidate();
    SecurityContextHolder.clearContext();
    log.info("****User logged out****");
    return new LogoutRes("Logout successful", true);
  }

  private String generateOtp() {
    SecureRandom sr = new SecureRandom();
    int otp = 100000 + sr.nextInt(900000);
    log.info("****OTP generated****");
    return String.valueOf(otp);
  }

  private String generateUUID() {
    log.info("****UUID Generated****");
    return UUID.randomUUID().toString();
  }
}
