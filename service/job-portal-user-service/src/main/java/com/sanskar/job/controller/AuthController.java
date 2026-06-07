package com.sanskar.job.controller;

import com.sanskar.job.payload.AuthResponse;
import com.sanskar.job.payload.LoginRequest;
import com.sanskar.job.payload.SignUpRequest;
import com.sanskar.job.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@RequestBody @Valid SignUpRequest req) throws Exception {
        return ResponseEntity.ok(authService.signUp(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest req) throws Exception {
        return ResponseEntity.ok(authService.login(req));
    }
}
