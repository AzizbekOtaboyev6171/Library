CREATE TABLE loans
(
    loan_id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    book_id          BIGINT                      NOT NULL,
    member_id        BIGINT                      NOT NULL,
    issue_date       DATE                        NOT NULL,
    due_date         DATE                        NOT NULL,
    return_date      DATE                        NULL,
    status           ENUM ('ISSUED', 'RETURNED') NOT NULL DEFAULT 'ISSUED',
    created_at       TIMESTAMP                   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP                   NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    created_by       BIGINT                      NOT NULL,
    last_modified_by BIGINT                      NULL,
    version          BIGINT                      NOT NULL DEFAULT 0,
    CONSTRAINT fk_loans_book FOREIGN KEY (book_id) REFERENCES books (book_id) ON DELETE CASCADE,
    CONSTRAINT fk_loans_member FOREIGN KEY (member_id) REFERENCES members (member_id) ON DELETE CASCADE
);

CREATE INDEX idx_loans_loan_id ON loans (loan_id);
CREATE INDEX idx_loans_book_id ON loans (book_id);
CREATE INDEX idx_loans_member_id ON loans (member_id);