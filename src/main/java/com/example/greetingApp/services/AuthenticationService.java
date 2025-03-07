package com.example.greetingApp.services;

import com.example.greetingApp.config.JwtUtil;
import com.example.greetingApp.dto.AuthUserDTO;
import com.example.greetingApp.dto.LoginDTO;
import com.example.greetingApp.entity.AuthUser;
import com.example.greetingApp.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final JavaMailSender mailSender;

    @Autowired
    public AuthenticationService(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, JavaMailSender mailSender) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.mailSender = mailSender;
    }

    public String registerUser(AuthUserDTO authUserDTO) {
        if (authUserRepository.findByEmail(authUserDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        AuthUser user = new AuthUser();
        user.setFirstName(authUserDTO.getFirstName());
        user.setLastName(authUserDTO.getLastName());
        user.setEmail(authUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(authUserDTO.getPassword()));

        authUserRepository.save(user);
        return "Registration successful" +  jwtUtil.generateToken(user.getEmail());
    }

    public String loginUser(LoginDTO loginDTO) {
        Optional<AuthUser> userOptional = authUserRepository.findByEmail(loginDTO.getEmail());

        if (userOptional.isEmpty() || !passwordEncoder.matches(loginDTO.getPassword(), userOptional.get().getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        sendLoginNotification(userOptional.get().getEmail());
        String token=jwtUtil.generateToken(userOptional.get().getEmail());
        return "Login successful, token: "+token;
    }
    public void sendLoginNotification(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Login Notification");
        message.setText("Hello,\n\nYou have successfully logged into your account.\n\nIf this wasn't you, please secure your account.");
        mailSender.send(message);
        System.out.println("Login notification email sent to: " + email);
    }
    public String forgotPassword(String email, String newPassword) {
        Optional<AuthUser> userOptional = authUserRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Sorry! We cannot find the user email: " + email);
        }

        AuthUser user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        authUserRepository.save(user);

        sendPasswordUpdateNotification(email);
        return "Password has been changed successfully!";
    }

    public String resetPassword(String email, String currentPassword, String newPassword) {
        Optional<AuthUser> userOptional = authUserRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with email: " + email);
        }

        AuthUser user = userOptional.get();
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new RuntimeException("Current password is incorrect!");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        authUserRepository.save(user);

        sendPasswordUpdateNotification(email);
        return "Password reset successfully!";
    }

    private void sendPasswordUpdateNotification(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Update Confirmation");
        message.setText("Your password has been successfully updated.");
        mailSender.send(message);
        System.out.println("Password update notification email sent to: " + email);
    }
}