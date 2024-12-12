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
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", indexes = {
        @Index(name = "idx_user_id", columnList = "user_id"),
        @Index(name = "idx_user_username", columnList = "username")
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long id;
    @Column(name = "username", nullable = false, unique = true)
    String username;
    @Column(name = "password", nullable = false)
    String password;
    @Column(name = "work_time")
    Integer workTime;
    @Column(name = "last_login")
    LocalDateTime lastLogin;
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

    // Agar loyihaga role qo'shmoqchi bo'lsangiz

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    Set<Role> roles;

}