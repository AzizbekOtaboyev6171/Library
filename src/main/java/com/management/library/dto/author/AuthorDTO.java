package com.management.library.dto.author;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public record AuthorDTO(
        Long id,
        String firstName,
        String lastName,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        Timestamp createdAt,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        Timestamp lastModifiedAt,
        Long createdBy,
        Long lastModifiedBy,
        long version
) {
}