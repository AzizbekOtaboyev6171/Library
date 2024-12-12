package com.management.library.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authors", indexes = {
        @Index(name = "idx_author_id", columnList = "author_id"),
        @Index(name = "idx_author_first_name", columnList = "first_name"),
        @Index(name = "idx_author_last_name", columnList = "last_name")
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    Long id;
    @Column(name = "first_name", nullable = false, length = 50)
    String firstName;
    @Column(name = "last_name", nullable = false, length = 50)
    String lastName;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    Timestamp createdAt;
    @LastModifiedDate
    @Column(name = "last_modified_at", insertable = false)
    Timestamp lastModifiedAt;
    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    Long createdBy;
    @LastModifiedBy
    @Column(name = "last_modified_by", insertable = false)
    Long lastModifiedBy;
    @Version
    @Column(name = "version")
    long version;
}