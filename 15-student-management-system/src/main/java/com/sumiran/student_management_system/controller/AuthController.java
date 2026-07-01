package com.sumiran.student_management_system.controller;

import com.sumiran.student_management_system.dto.LoginRequest;
import com.sumiran.student_management_system.dto.LoginResponse;
import com.sumiran.student_management_system.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    private final String DEMO_USER = "sumiran";
    private final String DEMO_PASS;

    public AuthController(JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.DEMO_PASS = passwordEncoder.encode("password123");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (DEMO_USER.equals(request.getUsername()) &&
                passwordEncoder.matches(
                        request.getPassword(), DEMO_PASS)) {
            String token = jwtUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(new LoginResponse(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid credentials");
    }
}