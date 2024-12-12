package com.management.library.dto.loan;

import jakarta.validation.constraints.NotNull;

public record LoanReturnDTO(
        @NotNull(message = "Loan ID is mandatory")
        Long loanId
) {
}