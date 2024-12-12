package com.management.library.controller;

import com.management.library.dto.loan.LoanDTO;
import com.management.library.dto.loan.LoanIssueDTO;
import com.management.library.dto.loan.LoanReturnDTO;
import com.management.library.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/loan")
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/issue_loan")
    @Operation(summary = "Issue a loan", tags = {"Loan"})
    public ResponseEntity<LoanDTO> issueLoan(@Valid @RequestBody LoanIssueDTO loanIssueDTO) {
        return ResponseEntity.ok(loanService.issueLoan(loanIssueDTO));
    }

    @PostMapping("/return_loan")
    @Operation(summary = "Return a loan", tags = {"Loan"})
    public ResponseEntity<LoanDTO> returnLoan(@Valid @RequestBody LoanReturnDTO loanReturnDTO) {
        return ResponseEntity.ok(loanService.returnLoan(loanReturnDTO));
    }
}