package com.management.library.mapper;

import com.management.library.dto.author.AuthorDTO;
import com.management.library.entity.Author;
import com.management.library.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AuthorMapper extends EntityMapper<AuthorDTO, Author> {
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "lastModifiedBy.id", target = "lastModifiedBy")
    AuthorDTO toDTO(Author author);

    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "authorMapIdToUser")
    @Mapping(source = "lastModifiedBy", target = "lastModifiedBy", qualifiedByName = "authorMapIdToUser")
    Author toEntity(AuthorDTO authorDTO);

    @Named("authorMapIdToUser")
    default User mapIdToUser(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}