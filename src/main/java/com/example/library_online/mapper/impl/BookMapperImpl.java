package com.example.library_online.mapper.impl;

import com.example.library_online.dto.book.BookResponse;
import com.example.library_online.entities.Book;
import com.example.library_online.mapper.BookMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapperImpl implements BookMapper {
    @Override
    public List<BookResponse> toDtoS(List<Book> all) {
        List<BookResponse> bookResponses = new ArrayList<>();
        for (Book book: all){
            bookResponses.add(toDto(book));
        }
        return bookResponses;
    }

    @Override
    public BookResponse toDto(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setName(book.getName());
        bookResponse.setExist(book.getExist());
        bookResponse.setTranscript(book.getTranscript());
        bookResponse.setAge_access(book.getAgeAccess());
        bookResponse.setPrize(book.getPrize());
        bookResponse.setCreated_date(book.getCreated_date());
        bookResponse.setAuthor_full_name(book.getAuthor_full_name());
        bookResponse.setType(book.getType().getName());
        return bookResponse;
    }
}
