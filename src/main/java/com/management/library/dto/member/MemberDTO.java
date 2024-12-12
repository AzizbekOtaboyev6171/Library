package com.management.library.dto.member;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.time.LocalDate;

public record MemberDTO(
        Long id,
        String name,
        String email,
        String phone,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate membershipDate,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        Timestamp createdAt,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        Timestamp lastModifiedAt,
        Long createdBy,
        Long lastModifiedBy,
        long version
) {
}