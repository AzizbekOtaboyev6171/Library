package com.management.library.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record BookCreateDTO(
        @NotBlank(message = "Book title is mandatory")
        @Size(min = 3, max = 100, message = "Book title must be between {min} and {max} characters long")
        String title,
        @NotNull(message = "Book author id is mandatory")
        Long authorId,
        @NotNull(message = "Book genre id is mandatory")
        Long genreId,
        @NotNull(message = "Book count is mandatory")
        @PositiveOrZero(message = "Book count must be positive or zero")
        Long count
) {
}