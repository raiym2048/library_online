package com.example.library_online.service;

import com.example.library_online.dto.book.BookAddRequest;
import com.example.library_online.dto.book.BookResponse;

import java.util.List;

public interface BookService {
    void addBook(BookAddRequest request, String token);

    List<BookResponse> getAll(String s);

    void buy(Long bookId, String token);

    List<BookResponse> getMyBooks(String string);
}
