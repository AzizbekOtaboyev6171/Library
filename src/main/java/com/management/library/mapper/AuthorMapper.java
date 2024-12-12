package com.management.library.mapper;

import com.management.library.dto.author.AuthorDTO;
import com.management.library.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper extends EntityMapper<AuthorDTO, Author> {
}