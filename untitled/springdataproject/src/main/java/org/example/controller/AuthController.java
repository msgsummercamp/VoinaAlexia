package org.example.controller;

import org.example.dto.SignInRequest;
import org.example.dto.SignInResponse;
import org.example.dto.UserRequest;
import org.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<SignInResponse> login(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest request) {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully.");
    }
}
