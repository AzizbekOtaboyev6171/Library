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
@Table(name = "genres", indexes = {
        @Index(name = "idx_genre_id", columnList = "genre_id"),
        @Index(name = "idx_genre_title", columnList = "title")
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    Long id;
    @Column(name = "title", nullable = false, unique = true, length = 50)
    String title;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    Timestamp createdAt;
    @LastModifiedDate
    @Column(name = "last_modified_at", insertable = false)
    Timestamp lastModifiedAt;
    @CreatedBy
    @JoinColumn(name = "created_by", nullable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    User createdBy;
    @LastModifiedBy
    @JoinColumn(name = "last_modified_by", insertable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    User lastModifiedBy;
    @Version
    @Column(name = "version")
    long version;
}