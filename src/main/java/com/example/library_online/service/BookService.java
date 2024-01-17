package com.example.library_online.service;

import com.example.library_online.dto.book.BookAddRequest;

public interface BookService {
    void addBook(BookAddRequest request, String token);
}
