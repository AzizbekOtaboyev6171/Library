package com.management.library.dto.auth;

public record TokenRefreshResponseDTO(
        String accessToken,
        String refreshToken
) {
}