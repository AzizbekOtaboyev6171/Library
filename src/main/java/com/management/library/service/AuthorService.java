package com.management.library.service;

import com.management.library.dto.author.AuthorCreateDTO;
import com.management.library.dto.author.AuthorDTO;
import com.management.library.dto.author.AuthorUpdateDTO;
import org.springframework.data.domain.Page;

public interface AuthorService {
    AuthorDTO createAuthor(AuthorCreateDTO authorCreateDTO);

    AuthorDTO updateAuthor(Long id, AuthorUpdateDTO authorUpdateDTO);

    AuthorDTO getAuthorById(Long id);

    void deleteAuthorById(Long id);

    Page<AuthorDTO> getAllAuthors(int page, int size);
}