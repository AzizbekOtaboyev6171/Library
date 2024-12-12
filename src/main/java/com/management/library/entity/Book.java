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
@Table(name = "books", indexes = {
        @Index(name = "idx_book_id", columnList = "book_id"),
        @Index(name = "idx_book_title", columnList = "title")
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    Long id;
    @Column(name = "title", nullable = false, length = 100)
    String title;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    Author author;
    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    Genre genre;
    @Column(name = "count", nullable = false)
    long count;
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