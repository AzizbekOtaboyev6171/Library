package com.management.library.service;

import com.management.library.dto.book.BookCreateDTO;
import com.management.library.dto.book.BookDTO;
import com.management.library.dto.book.BookUpdateDTO;
import org.springframework.data.domain.Page;

public interface BookService {
    BookDTO createBook(BookCreateDTO bookCreateDTO);

    BookDTO updateBook(Long id, BookUpdateDTO bookUpdateDTO);

    BookDTO getBookById(Long id);

    void deleteBookById(Long id);

    Page<BookDTO> getBooks(int page, int size);

    Page<BookDTO> searchBooksByKeyword(String keyword, int page, int size);

    Page<BookDTO> filterBooksByAuthorAndGenre(Long authorId, Long genreId, int page, int size);
}