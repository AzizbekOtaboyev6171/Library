package com.management.library.service.impl;

import com.management.library.dto.loan.LoanDTO;
import com.management.library.dto.loan.LoanIssueDTO;
import com.management.library.dto.loan.LoanReturnDTO;
import com.management.library.entity.Book;
import com.management.library.entity.Loan;
import com.management.library.entity.Member;
import com.management.library.exceptions.BookNotAvailableException;
import com.management.library.exceptions.LoanAlreadyReturnedException;
import com.management.library.exceptions.ResourceNotFoundException;
import com.management.library.mapper.LoanMapper;
import com.management.library.repository.BookRepository;
import com.management.library.repository.LoanRepository;
import com.management.library.repository.MemberRepository;
import com.management.library.service.LoanService;
import com.management.library.status.LoanStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    @Override
    public LoanDTO issueLoan(LoanIssueDTO loanIssueDTO) {
        log.info("Issuing loan for member: {} and book: {}", loanIssueDTO.memberId(), loanIssueDTO.bookId());
        Member member = memberRepository.findById(loanIssueDTO.memberId())
                .orElseThrow(() -> new ResourceNotFoundException("Member with id " + loanIssueDTO.memberId() + " not found"));
        Book book = bookRepository.findById(loanIssueDTO.bookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + loanIssueDTO.bookId() + " not found"));
        long activeLoans = loanRepository.countActiveLoans(member.getId());
        if (activeLoans >= book.getCount()) {
            throw new BookNotAvailableException("Book with id " + book.getId() + " is not available");
        }
        Loan loan = Loan.builder()
                .member(member)
                .book(book)
                .issueDate(loanIssueDTO.issueDate())
                .dueDate(loanIssueDTO.dueDate())
                .build();
        Loan saved = loanRepository.save(loan);
        log.info("Loan with id: {} issued successfully!", saved.getId());
        return loanMapper.toDTO(saved);
    }

    @Override
    public LoanDTO returnLoan(LoanReturnDTO loanReturnDTO) {
        log.info("Returning loan with id: {}", loanReturnDTO.loanId());
        Loan loan = loanRepository.findById(loanReturnDTO.loanId())
                .orElseThrow(() -> new ResourceNotFoundException("Loan with id " + loanReturnDTO.loanId() + " not found"));
        if (loan.getStatus() == LoanStatus.RETURNED) {
            throw new LoanAlreadyReturnedException("Loan with id " + loan.getId() + " already returned");
        }
        loan.setReturnDate(LocalDate.now());
        loan.setStatus(LoanStatus.RETURNED);
        Loan saved = loanRepository.save(loan);
        log.info("Loan with id: {} returned successfully!", saved.getId());
        return loanMapper.toDTO(saved);
    }
}