package com.management.library.dto.role;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.management.library.dto.permission.PermissionDTO;

import java.sql.Timestamp;
import java.util.Set;

public record RoleDTO(
        Long id,
        String name,
        String description,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        Timestamp createdAt,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        Timestamp lastModifiedAt,
        Long createdBy,
        Long lastModifiedBy,
        long version,
        Set<PermissionDTO> permissions
) {
}