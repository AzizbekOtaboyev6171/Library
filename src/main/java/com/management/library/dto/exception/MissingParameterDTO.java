package com.management.library.dto.exception;

public record MissingParameterDTO(
        String field,
        String message
) {
}
