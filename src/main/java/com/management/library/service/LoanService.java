package com.management.library.service;

import com.management.library.dto.loan.LoanDTO;
import com.management.library.dto.loan.LoanIssueDTO;
import com.management.library.dto.loan.LoanReturnDTO;

public interface LoanService {
    LoanDTO issueLoan(LoanIssueDTO loanIssueDTO);

    LoanDTO returnLoan(LoanReturnDTO loanReturnDTO);
}