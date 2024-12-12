package com.management.library.configuration;

import com.management.library.dto.auth.LoginRequestDTO;
import com.management.library.dto.auth.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

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
}