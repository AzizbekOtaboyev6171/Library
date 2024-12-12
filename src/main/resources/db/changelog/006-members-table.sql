CREATE TABLE members
(
    member_id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(100) NOT NULL,
    email            VARCHAR(50)  NOT NULL UNIQUE,
    phone            VARCHAR(15)  NOT NULL UNIQUE,
    membership_date  DATE         NOT NULL,
    created_at       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP    NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    created_by       BIGINT       NOT NULL,
    last_modified_by BIGINT       NULL,
    version          BIGINT       NOT NULL DEFAULT 0
);

CREATE INDEX idx_member_id ON members (member_id);
CREATE INDEX idx_member_email ON members (email);
CREATE INDEX idx_member_phone ON members (phone);