package com.management.library.dto.loan;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record LoanIssueDTO(
        @NotNull(message = "Member ID is mandatory")
        Long memberId,
        @NotNull(message = "Book ID is mandatory")
        Long bookId,
        @NotNull(message = "Issue Date is mandatory")
        @PastOrPresent(message = "Issue Date should be in the past or present")
        LocalDate issueDate,
        @NotNull(message = "Due Date is mandatory")
        @FutureOrPresent(message = "Due Date should be in the future or present")
        LocalDate dueDate
) {
}