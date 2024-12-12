package com.management.library.service.impl;

import com.management.library.dto.author.AuthorCreateDTO;
import com.management.library.dto.author.AuthorDTO;
import com.management.library.dto.author.AuthorUpdateDTO;
import com.management.library.entity.Author;
import com.management.library.exceptions.ResourceNotFoundException;
import com.management.library.mapper.AuthorMapper;
import com.management.library.repository.AuthorRepository;
import com.management.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public AuthorDTO createAuthor(AuthorCreateDTO authorCreateDTO) {
        log.info("Creating author with first name: {} and last name: {}", authorCreateDTO.firstName(), authorCreateDTO.lastName());
        Author author = new Author();
        author.setFirstName(authorCreateDTO.firstName());
        author.setLastName(authorCreateDTO.lastName());
        Author saved = authorRepository.save(author);
        log.info("Author with id: {} created successfully!", saved.getId());
        return authorMapper.toDTO(saved);
    }

    @Override
    public AuthorDTO updateAuthor(Long id, AuthorUpdateDTO authorUpdateDTO) {
        log.info("Updating author with id: {}", id);
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
        author.setFirstName(authorUpdateDTO.firstName());
        author.setLastName(authorUpdateDTO.lastName());
        Author saved = authorRepository.save(author);
        log.info("Author with id: {} updated successfully!", id);
        return authorMapper.toDTO(saved);
    }

    @Override
    public AuthorDTO getAuthorById(Long id) {
        log.info("Getting author with id: {}", id);
        return authorRepository.findById(id)
                .map(authorMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
    }

    @Override
    public void deleteAuthorById(Long id) {
        log.info("Deleting author with id: {}", id);
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author with id " + id + " not found");
        }
        authorRepository.deleteById(id);
        log.info("Author with id: {} deleted successfully!", id);
    }

    @Override
    public Page<AuthorDTO> getAllAuthors(int page, int size) {
        log.info("Getting all authors");
        return authorRepository.findAll(PageRequest.of(page, size))
                .map(authorMapper::toDTO);
    }
}