package com.management.library.dto.user;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record UserDTO(
        Long id,
        String username,
        String password,
        Integer workTime,
        LocalDateTime lastLogin,
        Timestamp createdAt,
        Timestamp lastModifiedAt,
        Long createdBy,
        Long lastModifiedBy,
        long version

//        Set<Role> roles

) {
}