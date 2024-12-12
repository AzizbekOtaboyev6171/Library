package com.management.library.repository;

import com.management.library.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Boolean existsByTitleEqualsIgnoreCase(String title);

    Boolean existsByTitleEqualsIgnoreCaseAndIdNot(String title, Long id);
}