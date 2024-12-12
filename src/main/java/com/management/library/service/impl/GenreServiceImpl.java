package com.management.library.service.impl;

import com.management.library.dto.genre.GenreCreateDTO;
import com.management.library.dto.genre.GenreDTO;
import com.management.library.dto.genre.GenreUpdateDTO;
import com.management.library.entity.Genre;
import com.management.library.exceptions.ResourceAlreadyExistsException;
import com.management.library.exceptions.ResourceNotFoundException;
import com.management.library.mapper.GenreMapper;
import com.management.library.repository.GenreRepository;
import com.management.library.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public GenreDTO createGenre(GenreCreateDTO genreCreateDTO) {
        log.info("Creating genre with title: {}", genreCreateDTO.title());
        if (genreRepository.existsByTitleEqualsIgnoreCase(genreCreateDTO.title())) {
            throw new ResourceAlreadyExistsException("Genre with title " + genreCreateDTO.title() + " already exists");
        }
        Genre genre = new Genre();
        genre.setTitle(genreCreateDTO.title());
        Genre saved = genreRepository.save(genre);
        log.info("Genre with id: {} created successfully!", saved.getId());
        return genreMapper.toDTO(saved);
    }

    @Override
    public GenreDTO updateGenre(Long id, GenreUpdateDTO genreUpdateDTO) {
        log.info("Updating genre with id: {}", id);
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre with id " + id + " not found"));
        if (genreRepository.existsByTitleEqualsIgnoreCaseAndIdNot(genreUpdateDTO.title(), id)) {
            throw new ResourceAlreadyExistsException("Genre with title " + genreUpdateDTO.title() + " already exists");
        }
        genre.setTitle(genreUpdateDTO.title());
        Genre saved = genreRepository.save(genre);
        log.info("Genre with id: {} updated successfully!", id);
        return genreMapper.toDTO(saved);
    }

    @Override
    public GenreDTO getGenreById(Long id) {
        log.info("Getting genre with id: {}", id);
        return genreRepository.findById(id)
                .map(genreMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Genre with id " + id + " not found"));
    }

    @Override
    public void deleteGenreById(Long id) {
        log.info("Deleting genre with id: {}", id);
        if (!genreRepository.existsById(id)) {
            throw new ResourceNotFoundException("Genre with id " + id + " not found");
        }
        genreRepository.deleteById(id);
        log.info("Genre with id: {} deleted successfully!", id);
    }

    @Override
    public Page<GenreDTO> getAllGenres(int page, int size) {
        log.info("Getting all genres");
        return genreRepository.findAll(PageRequest.of(page, size))
                .map(genreMapper::toDTO);
    }
}