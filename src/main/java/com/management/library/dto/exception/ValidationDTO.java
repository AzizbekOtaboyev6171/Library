package com.management.library.dto.exception;

public record ValidationDTO(
        String field,
        String message
) {
}