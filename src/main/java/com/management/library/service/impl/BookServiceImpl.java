package com.management.library.service.impl;

import com.management.library.dto.book.BookCreateDTO;
import com.management.library.dto.book.BookDTO;
import com.management.library.dto.book.BookUpdateDTO;
import com.management.library.entity.Author;
import com.management.library.entity.Book;
import com.management.library.entity.Genre;
import com.management.library.exceptions.ResourceNotFoundException;
import com.management.library.mapper.BookMapper;
import com.management.library.repository.AuthorRepository;
import com.management.library.repository.BookRepository;
import com.management.library.repository.GenreRepository;
import com.management.library.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public BookDTO createBook(BookCreateDTO bookCreateDTO) {
        log.info("Creating a new book with title: {}", bookCreateDTO.title());
        Author author = authorRepository.findById(bookCreateDTO.authorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + bookCreateDTO.authorId() + " not found"));
        Genre genre = genreRepository.findById(bookCreateDTO.genreId())
                .orElseThrow(() -> new ResourceNotFoundException("Genre with id " + bookCreateDTO.genreId() + " not found"));
        Book book = new Book();
        book.setTitle(bookCreateDTO.title());
        book.setAuthor(author);
        book.setGenre(genre);
        book.setCount(bookCreateDTO.count());
        Book saved = bookRepository.save(book);
        log.info("Book with id: {} created successfully!", saved.getId());
        return bookMapper.toDTO(saved);
    }

    @Override
    public BookDTO updateBook(Long id, BookUpdateDTO bookUpdateDTO) {
        log.info("Updating book with id: {}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        Author author = authorRepository.findById(bookUpdateDTO.authorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + bookUpdateDTO.authorId() + " not found"));
        Genre genre = genreRepository.findById(bookUpdateDTO.genreId())
                .orElseThrow(() -> new ResourceNotFoundException("Genre with id " + bookUpdateDTO.genreId() + " not found"));
        book.setTitle(bookUpdateDTO.title());
        book.setAuthor(author);
        book.setGenre(genre);
        book.setCount(bookUpdateDTO.count());
        Book updated = bookRepository.save(book);
        log.info("Book with id: {} updated successfully!", updated.getId());
        return bookMapper.toDTO(updated);
    }

    @Override
    public BookDTO getBookById(Long id) {
        log.info("Getting book with id: {}", id);
        return bookRepository.findById(id)
                .map(bookMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
    }

    @Override
    public void deleteBookById(Long id) {
        log.info("Deleting book with id: {}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        bookRepository.deleteById(id);
        log.info("Book with id: {} deleted successfully!", id);
    }

    @Override
    public Page<BookDTO> getBooks(int page, int size) {
        log.info("Getting all books");
        return bookRepository.findAll(PageRequest.of(page, size))
                .map(bookMapper::toDTO);
    }

    @Override
    public Page<BookDTO> searchBooksByKeyword(String keyword, int page, int size) {
        log.info("Searching books by keyword: {}", keyword);
        return bookRepository.searchBooksByKeyword(keyword, PageRequest.of(page, size))
                .map(bookMapper::toDTO);
    }

    @Override
    public Page<BookDTO> filterBooksByAuthorAndGenre(Long authorId, Long genreId, int page, int size) {
        log.info("Filtering books by author id: {} and genre id: {}", authorId, genreId);
        return bookRepository.findBooksByAuthorAndGenre(authorId, genreId, PageRequest.of(page, size))
                .map(bookMapper::toDTO);
    }
}