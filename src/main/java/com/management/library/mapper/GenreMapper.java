package com.management.library.mapper;

import com.management.library.dto.genre.GenreDTO;
import com.management.library.entity.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper extends EntityMapper<GenreDTO, Genre> {
}