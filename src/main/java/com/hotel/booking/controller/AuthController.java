package com.hotel.booking.controller;

import com.hotel.booking.config.JwtUtil;
import com.hotel.booking.dto.LoginRequest;
import com.hotel.booking.dto.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String role = userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        String token = jwtUtil.generateToken(userDetails.getUsername(), role);

        return ResponseEntity.ok(new LoginResponse(token, userDetails.getUsername(), role));
    }
}