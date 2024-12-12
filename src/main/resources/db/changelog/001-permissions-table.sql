CREATE TABLE permissions
(
    permission_id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    permission_name  VARCHAR(100) NOT NULL UNIQUE,
    description      VARCHAR(255),
    created_at       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP    NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);

CREATE INDEX idx_permission_id ON permissions (permission_id);
CREATE INDEX idx_permission_name ON permissions (permission_name);