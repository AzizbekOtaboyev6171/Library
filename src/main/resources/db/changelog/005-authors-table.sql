CREATE TABLE authors
(
    author_id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name       VARCHAR(50) NOT NULL,
    last_name        VARCHAR(50) NOT NULL,
    created_at       TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP   NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    created_by       BIGINT      NOT NULL,
    last_modified_by BIGINT      NULL,
    version          BIGINT      NOT NULL DEFAULT 0
);

CREATE INDEX idx_authors_author_id ON authors (author_id);
CREATE INDEX idx_authors_first_name ON authors (first_name);
CREATE INDEX idx_authors_last_name ON authors (last_name);