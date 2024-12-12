package com.management.library.controller;

import com.management.library.dto.author.AuthorCreateDTO;
import com.management.library.dto.author.AuthorDTO;
import com.management.library.dto.author.AuthorUpdateDTO;
import com.management.library.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/create_author")
    @Operation(summary = "Create a new author", tags = {"Author"})
    public ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody AuthorCreateDTO authorCreateDTO) {
        return ResponseEntity.ok(authorService.createAuthor(authorCreateDTO));
    }

    @PutMapping("/update_author/{id}")
    @Operation(summary = "Update an author", tags = {"Author"})
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable(name = "id") Long id, @Valid @RequestBody AuthorUpdateDTO authorUpdateDTO) {
        return ResponseEntity.ok(authorService.updateAuthor(id, authorUpdateDTO));
    }

    @GetMapping("/get_author/{id}")
    @Operation(summary = "Get an author", tags = {"Author"})
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @DeleteMapping("/delete_author/{id}")
    @Operation(summary = "Delete an author", tags = {"Author"})
    public ResponseEntity<String> deleteAuthor(@PathVariable(name = "id") Long id) {
        authorService.deleteAuthorById(id);
        return ResponseEntity.ok("Author deleted successfully!");
    }

    @GetMapping("/get_authors")
    @Operation(summary = "Get all authors", tags = {"Author"})
    public ResponseEntity<Page<AuthorDTO>> getAuthors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(authorService.getAllAuthors(page, size));
    }
}