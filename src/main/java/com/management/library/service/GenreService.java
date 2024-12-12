package com.management.library.service;

import com.management.library.dto.genre.GenreCreateDTO;
import com.management.library.dto.genre.GenreDTO;
import com.management.library.dto.genre.GenreUpdateDTO;
import org.springframework.data.domain.Page;

public interface GenreService {
    GenreDTO createGenre(GenreCreateDTO genreCreateDTO);

    GenreDTO updateGenre(Long id, GenreUpdateDTO genreUpdateDTO);

    GenreDTO getGenreById(Long id);

    void deleteGenreById(Long id);

    Page<GenreDTO> getAllGenres(int page, int size);
}