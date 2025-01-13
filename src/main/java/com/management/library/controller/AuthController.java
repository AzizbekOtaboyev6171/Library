package com.management.library.controller;

import com.management.library.configuration.AuthService;
import com.management.library.dto.auth.LoginRequestDTO;
import com.management.library.dto.auth.LoginResponseDTO;
import com.management.library.dto.auth.TokenRefreshRequestDTO;
import com.management.library.dto.auth.TokenRefreshResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Login", tags = {"Auth"})
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "Refresh token", tags = {"Auth"})
    public ResponseEntity<TokenRefreshResponseDTO> refreshToken(@Valid @RequestBody TokenRefreshRequestDTO tokenRefreshRequest) {
        return ResponseEntity.ok(authService.refreshToken(tokenRefreshRequest));
    }
}