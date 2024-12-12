package com.management.library.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreateDTO(
        @NotBlank(message = "Username is mandatory")
        @Size(min = 3, max = 50, message = "Username must be between {min} and {max} characters long")
        String username,

        @NotBlank(message = "Password is mandatory")
        @Size(min = 8, message = "Password must be at least {min} characters long")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
        )
        String password

        // Agar loyihaga role qo'shmoqchi bo'lsangiz

//        @NotNull(message = "User roles are mandatory")
//        @Size(min = 1, message = "At least one role must be selected")
//        Set<Long> roles

) {
}