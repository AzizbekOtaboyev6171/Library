package com.management.library.dto.member;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record MemberCreateDTO(
        @NotBlank(message = "Member name is mandatory")
        @Size(min = 3, max = 100, message = "Member name must be between {min} and {max} characters long")
        String name,
        @NotBlank(message = "Member email is mandatory")
        @Size(min = 3, max = 50, message = "Member email must be between {min} and {max} characters long")
        @Email(message = "Member email must be a valid email address")
        String email,
        @NotBlank(message = "Member phone is mandatory")
        @Pattern(regexp = "^\\+998[0-9]{9}$", message = "Member phone must be a valid Uzbekistan format (+998XXXXXXXXX)")
        String phone,
        @NotNull(message = "Member membership date is mandatory")
        LocalDate membershipDate
) {
}