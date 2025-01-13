package com.management.library.mapper;

import com.management.library.dto.book.BookDTO;
import com.management.library.entity.Book;
import com.management.library.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, GenreMapper.class})
public interface BookMapper extends EntityMapper<BookDTO, Book> {
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "lastModifiedBy.id", target = "lastModifiedBy")
    BookDTO toDTO(Book book);

    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "bookMapIdToUser")
    @Mapping(source = "lastModifiedBy", target = "lastModifiedBy", qualifiedByName = "bookMapIdToUser")
    Book toEntity(BookDTO bookDTO);

    @Named("bookMapIdToUser")
    default User mapIdToUser(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}