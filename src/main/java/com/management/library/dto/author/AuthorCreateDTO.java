package com.management.library.dto.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthorCreateDTO(
        @NotBlank(message = "Author first name is mandatory")
        @Size(min = 3, max = 50, message = "Author first name must be between {min} and {max} characters long")
        String firstName,
        @NotBlank(message = "Author last name is mandatory")
        @Size(min = 3, max = 50, message = "Author last name must be between {min} and {max} characters long")
        String lastName
) {
}