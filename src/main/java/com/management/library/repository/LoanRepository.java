package com.management.library.repository;

import com.management.library.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("SELECT COUNT(l) FROM Loan l WHERE " +
            "l.book.id = :bookId AND l.returnDate IS NULL")
    long countActiveLoans(@Param("bookId") Long bookId);
}