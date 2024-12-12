package com.management.library.dto.genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GenreUpdateDTO(
        @NotBlank(message = "Title is mandatory")
        @Size(min = 3, max = 50, message = "Title must be between {min} and {max} characters long")
        String title
) {
}