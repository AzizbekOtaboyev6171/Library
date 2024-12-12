package com.management.library.repository;

import com.management.library.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNameEqualsIgnoreCase(String name);

    Boolean existsByNameEqualsIgnoreCase(String name);

    Boolean existsByNameEqualsIgnoreCaseAndIdNot(String name, Long id);
}