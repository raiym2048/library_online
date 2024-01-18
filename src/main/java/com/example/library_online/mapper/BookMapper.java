package com.example.library_online.mapper;

import com.example.library_online.dto.book.BookResponse;
import com.example.library_online.entities.Book;

import java.util.List;

public interface BookMapper {
    List<BookResponse> toDtoS(List<Book> all);

    BookResponse toDto(Book book);
}
