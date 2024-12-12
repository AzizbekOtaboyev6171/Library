CREATE TABLE genres
(
    genre_id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    title            VARCHAR(50) NOT NULL UNIQUE,
    created_at       TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP   NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    created_by       BIGINT      NOT NULL,
    last_modified_by BIGINT      NULL,
    version          BIGINT      NOT NULL DEFAULT 0
);

CREATE INDEX idx_genre_id ON genres (genre_id);
CREATE INDEX idx_genre_title ON genres (title);