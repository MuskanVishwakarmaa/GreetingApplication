package com.example.greetingApp.controller;

import com.example.greetingApp.dto.AuthUserDTO;
import com.example.greetingApp.dto.LoginDTO;
import com.example.greetingApp.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AuthUserDTO authUserDTO) {
        return ResponseEntity.ok(authenticationService.registerUser(authUserDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(authenticationService.loginUser(loginDTO));
    }
    @PutMapping("/forgotPassword/{email}")
    public ResponseEntity<?> forgotPassword(@PathVariable String email, @RequestBody Map<String, String> request) {
        String newPassword = request.get("password");
        return ResponseEntity.ok(authenticationService.forgotPassword(email, newPassword));
    }

    @PutMapping("/resetPassword/{email}")
    public ResponseEntity<?> resetPassword(@PathVariable String email, @RequestBody Map<String, String> request) {
        String currentPassword = request.get("currentPassword");
        String newPassword = request.get("newPassword");
        return ResponseEntity.ok(authenticationService.resetPassword(email, currentPassword, newPassword));
    }

}