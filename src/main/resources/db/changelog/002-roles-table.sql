CREATE TABLE roles
(
    role_id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_name        VARCHAR(100) NOT NULL UNIQUE,
    description      VARCHAR(255),
    created_at       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP    NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    created_by       BIGINT       NOT NULL,
    last_modified_by BIGINT       NULL,
    version          BIGINT       NOT NULL DEFAULT 0
);

CREATE INDEX idx_role_id ON roles (role_id);
CREATE INDEX idx_role_name ON roles (role_name);

CREATE TABLE role_permissions
(
    role_id       BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES roles (role_id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES permissions (permission_id) ON DELETE CASCADE
);

CREATE INDEX idx_role_permissions_role_id ON role_permissions (role_id);
CREATE INDEX idx_role_permissions_permission_id ON role_permissions (permission_id);