package com.management.library.repository;

import com.management.library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(b.author.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(b.author.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Book> searchBooksByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE " +
            "(:authorId IS NULL OR b.author.id = :authorId) AND " +
            "(:genreId IS NULL OR b.genre.id = :genreId)")
    Page<Book> findBooksByAuthorAndGenre(@Param("authorId") Long authorId, @Param("genreId") Long genreId, Pageable pageable);
}