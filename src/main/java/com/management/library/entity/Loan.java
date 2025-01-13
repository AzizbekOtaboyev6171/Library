package com.management.library.entity;

import com.management.library.status.LoanStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loans", indexes = {
        @Index(name = "idx_loan_id", columnList = "loan_id"),
        @Index(name = "idx_loan_member_id", columnList = "member_id"),
        @Index(name = "idx_loan_book_id", columnList = "book_id")
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    Long id;
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    Member member;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    Book book;
    @Column(name = "issue_date", nullable = false)
    LocalDate issueDate;
    @Column(name = "due_date", nullable = false)
    LocalDate dueDate;
    @Column(name = "return_date")
    LocalDate returnDate;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "status", nullable = false)
    LoanStatus status = LoanStatus.ISSUED;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    Timestamp createdAt;
    @LastModifiedDate
    @Column(name = "last_modified_at", insertable = false)
    Timestamp lastModifiedAt;
    @CreatedBy
    @JoinColumn(name = "created_by", nullable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    User createdBy;
    @LastModifiedBy
    @JoinColumn(name = "last_modified_by", insertable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    User lastModifiedBy;
    @Version
    @Column(name = "version")
    long version;
}