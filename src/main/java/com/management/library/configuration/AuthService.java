package com.management.library.configuration;

import com.management.library.dto.auth.LoginRequestDTO;
import com.management.library.dto.auth.LoginResponseDTO;
import com.management.library.dto.auth.TokenRefreshRequestDTO;
import com.management.library.dto.auth.TokenRefreshResponseDTO;
import com.management.library.entity.User;
import com.management.library.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
            );
            PrincipalUser principal = (PrincipalUser) authentication.getPrincipal();
            String accessToken = jwtTokenProvider.generateAccessToken(principal);
            String refreshToken = jwtTokenProvider.generateRefreshToken(principal);
            return new LoginResponseDTO(accessToken, refreshToken);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        }
    }

    public TokenRefreshResponseDTO refreshToken(TokenRefreshRequestDTO tokenRefreshRequest) {
        if (!jwtTokenProvider.isRefreshTokenValid(tokenRefreshRequest.refreshToken())) {
            throw new JwtException("Invalid refresh token");
        }
        String userId = jwtTokenProvider.getUsernameFromRefreshToken(tokenRefreshRequest.refreshToken());
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String accessToken = jwtTokenProvider.generateAccessToken(PrincipalUser.builder().username(user.getUsername()).build());
        return new TokenRefreshResponseDTO(accessToken, tokenRefreshRequest.refreshToken());
    }
}