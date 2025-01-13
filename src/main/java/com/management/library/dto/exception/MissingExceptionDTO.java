package com.management.library.dto.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record MissingExceptionDTO(
        String message,
        int status,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
        LocalDateTime occurredAt,
        List<MissingParameterDTO> errors
) {
}