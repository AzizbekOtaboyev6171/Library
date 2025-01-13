package com.management.library.mapper;

import com.management.library.dto.genre.GenreDTO;
import com.management.library.entity.Genre;
import com.management.library.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface GenreMapper extends EntityMapper<GenreDTO, Genre> {
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "lastModifiedBy.id", target = "lastModifiedBy")
    GenreDTO toDTO(Genre genre);

    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "genreMapIdToUser")
    @Mapping(source = "lastModifiedBy", target = "lastModifiedBy", qualifiedByName = "genreMapIdToUser")
    Genre toEntity(GenreDTO genreDTO);

    @Named("genreMapIdToUser")
    default User mapIdToUser(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}