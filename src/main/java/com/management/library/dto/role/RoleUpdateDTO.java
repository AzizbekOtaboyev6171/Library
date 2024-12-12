package com.management.library.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record RoleUpdateDTO(
        @NotBlank(message = "Role name is mandatory")
        @Size(min = 3, max = 100, message = "Role name must be between {min} and {max} characters long")
        String name,
        @Size(max = 255, message = "Role description can be up to {max} characters long")
        String description,
        @NotNull(message = "Role permissions are mandatory")
        @Size(min = 1, message = "At least one permission must be selected")
        Set<Long> permissionIds
) {
}