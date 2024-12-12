package com.management.library.controller;

import com.management.library.dto.genre.GenreCreateDTO;
import com.management.library.dto.genre.GenreDTO;
import com.management.library.dto.genre.GenreUpdateDTO;
import com.management.library.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/genre")
public class GenreController {
    private final GenreService genreService;

    @PostMapping("/create_genre")
    @Operation(summary = "Create a new genre", tags = {"Genre"})
    public ResponseEntity<GenreDTO> createGenre(@Valid @RequestBody GenreCreateDTO genreCreateDTO) {
        return ResponseEntity.ok(genreService.createGenre(genreCreateDTO));
    }

    @PutMapping("/update_genre/{id}")
    @Operation(summary = "Update a genre", tags = {"Genre"})
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable(name = "id") Long id, @Valid @RequestBody GenreUpdateDTO genreUpdateDTO) {
        return ResponseEntity.ok(genreService.updateGenre(id, genreUpdateDTO));
    }

    @GetMapping("/get_genre/{id}")
    @Operation(summary = "Get a genre", tags = {"Genre"})
    public ResponseEntity<GenreDTO> getGenre(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(genreService.getGenreById(id));
    }

    @DeleteMapping("/delete_genre/{id}")
    @Operation(summary = "Delete a genre", tags = {"Genre"})
    public ResponseEntity<String> deleteGenre(@PathVariable(name = "id") Long id) {
        genreService.deleteGenreById(id);
        return ResponseEntity.ok("Genre deleted successfully!");
    }

    @GetMapping("/get_genres")
    @Operation(summary = "Get all genres", tags = {"Genre"})
    public ResponseEntity<Page<GenreDTO>> getGenres(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(genreService.getAllGenres(page, size));
    }
}