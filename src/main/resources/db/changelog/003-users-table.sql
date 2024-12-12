CREATE TABLE users
(
    user_id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    username         VARCHAR(255) NOT NULL UNIQUE,
    password         VARCHAR(255) NOT NULL,
    work_time        INT          NULL,
    last_login       TIMESTAMP    NULL,
    created_at       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP    NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    created_by       BIGINT       NOT NULL,
    last_modified_by BIGINT       NULL,
    version          BIGINT       NOT NULL DEFAULT 0
);

CREATE INDEX idx_users_id ON users (user_id);
CREATE INDEX idx_users_username ON users (username);

# CREATE TABLE user_roles
# (
#     user_id BIGINT NOT NULL,
#     role_id BIGINT NOT NULL,
#     PRIMARY KEY (user_id, role_id),
#     FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
#     FOREIGN KEY (role_id) REFERENCES roles (role_id) ON DELETE CASCADE
# );
#
# CREATE INDEX idx_user_roles_user_id ON user_roles (user_id);
# CREATE INDEX idx_user_roles_role_id ON user_roles (role_id);