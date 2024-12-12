package com.management.library.mapper;

import com.management.library.dto.book.BookDTO;
import com.management.library.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, GenreMapper.class})
public interface BookMapper extends EntityMapper<BookDTO, Book> {
}