package com.management.library.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "permissions", indexes = {
        @Index(name = "idx_permission_id", columnList = "permission_id"),
        @Index(name = "idx_permission_name", columnList = "permission_name"),
})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    Long id;
    @Column(name = "permission_name", nullable = false, unique = true, length = 100)
    String name;
    @Column(name = "description", length = 255)
    String description;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    Timestamp createdAt;
    @LastModifiedDate
    @Column(name = "last_modified_at", insertable = false)
    Timestamp lastModifiedAt;
}