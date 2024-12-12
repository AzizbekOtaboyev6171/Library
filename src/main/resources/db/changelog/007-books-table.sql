CREATE TABLE books
(
    book_id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title            VARCHAR(100) NOT NULL,
    author_id        BIGINT       NOT NULL,
    genre_id         BIGINT       NOT NULL,
    count            BIGINT       NOT NULL DEFAULT 0,
    created_at       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP    NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    created_by       BIGINT       NOT NULL,
    last_modified_by BIGINT       NULL,
    version          BIGINT       NOT NULL DEFAULT 0,
    CONSTRAINT fk_books_author FOREIGN KEY (author_id) REFERENCES authors (author_id) ON DELETE CASCADE,
    CONSTRAINT fk_books_genre FOREIGN KEY (genre_id) REFERENCES genres (genre_id) ON DELETE CASCADE
);

CREATE INDEX idx_books_book_id ON books (book_id);
CREATE INDEX idx_books_title ON books (title);