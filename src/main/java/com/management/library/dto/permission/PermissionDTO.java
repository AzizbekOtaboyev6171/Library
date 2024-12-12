package com.management.library.dto.permission;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public record PermissionDTO(
        Long id,
        String name,
        String description,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        Timestamp createdAt,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        Timestamp updatedAt
) {
}