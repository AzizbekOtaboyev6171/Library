package com.management.library.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record TokenRefreshRequestDTO(
        @NotBlank(message = "Refresh token is mandatory")
        String refreshToken
) {
}