package com.management.library.dto.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.management.library.dto.author.AuthorDTO;
import com.management.library.dto.genre.GenreDTO;

import java.sql.Timestamp;

public record BookDTO(
        Long id,
        String title,
        AuthorDTO author,
        GenreDTO genre,
        long count,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        Timestamp createdAt,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        Timestamp lastModifiedAt,
        Long createdBy,
        Long lastModifiedBy,
        long version
) {
}