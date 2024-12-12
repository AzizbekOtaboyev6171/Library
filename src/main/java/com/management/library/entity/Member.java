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
import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "members", indexes = {
        @Index(name = "idx_member_id", columnList = "member_id"),
        @Index(name = "idx_member_email", columnList = "email"),
        @Index(name = "idx_member_phone", columnList = "phone")
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    Long id;
    @Column(name = "name", nullable = false, length = 100)
    String name;
    @Column(name = "email", nullable = false, unique = true, length = 50)
    String email;
    @Column(name = "phone", nullable = false, unique = true, length = 15)
    String phone;
    @Column(name = "membership_date", nullable = false)
    LocalDate membershipDate;
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