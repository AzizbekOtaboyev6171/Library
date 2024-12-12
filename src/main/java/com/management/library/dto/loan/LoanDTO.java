package com.management.library.dto.loan;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.management.library.dto.book.BookDTO;
import com.management.library.dto.member.MemberDTO;
import com.management.library.status.LoanStatus;

import java.sql.Timestamp;
import java.time.LocalDate;

public record LoanDTO(
        Long id,
        MemberDTO member,
        BookDTO book,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate issueDate,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate dueDate,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate returnDate,
        LoanStatus status,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        Timestamp createdAt,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        Timestamp lastModifiedAt,
        Long createdBy,
        Long lastModifiedBy,
        long version
) {
}