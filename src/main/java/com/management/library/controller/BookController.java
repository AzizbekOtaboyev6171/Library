package com.management.library.controller;

import com.management.library.dto.book.BookCreateDTO;
import com.management.library.dto.book.BookDTO;
import com.management.library.dto.book.BookUpdateDTO;
import com.management.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @PostMapping("/create_book")
    @Operation(summary = "Create a new book", tags = {"Book"})
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookCreateDTO bookCreateDTO) {
        return ResponseEntity.ok(bookService.createBook(bookCreateDTO));
    }

    @PutMapping("/update_book/{id}")
    @Operation(summary = "Update a book", tags = {"Book"})
    public ResponseEntity<BookDTO> updateBook(@PathVariable(name = "id") Long id, @Valid @RequestBody BookUpdateDTO bookUpdateDTO) {
        return ResponseEntity.ok(bookService.updateBook(id, bookUpdateDTO));
    }

    @GetMapping("/get_book/{id}")
    @Operation(summary = "Get a book", tags = {"Book"})
    public ResponseEntity<BookDTO> getBook(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @DeleteMapping("/delete_book/{id}")
    @Operation(summary = "Delete a book", tags = {"Book"})
    public ResponseEntity<String> deleteBook(@PathVariable(name = "id") Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok("Book deleted successfully!");
    }

    @GetMapping("/get_books")
    @Operation(summary = "Get all books", tags = {"Book"})
    public ResponseEntity<Page<BookDTO>> getBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(bookService.getBooks(page, size));
    }

    @GetMapping("/search_books")
    @Operation(summary = "Search books by title and author", tags = {"Book"})
    public ResponseEntity<Page<BookDTO>> searchBooks(@RequestParam String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(bookService.searchBooksByKeyword(keyword, page, size));
    }

    @GetMapping("/filter_books")
    @Operation(summary = "Filter books by genre and author", tags = {"Book"})
    public ResponseEntity<Page<BookDTO>> filterBooks(@RequestParam(required = false) Long genreId, @RequestParam(required = false) Long authorId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(bookService.filterBooksByAuthorAndGenre(genreId, authorId, page, size));
    }
}