package com.management.library.dto.genre;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public record GenreDTO(
        Long id,
        String title,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        Timestamp createdAt,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        Timestamp lastModifiedAt,
        Long createdBy,
        Long lastModifiedBy,
        long version
) {
}