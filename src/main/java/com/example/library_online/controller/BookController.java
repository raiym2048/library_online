package com.example.library_online.controller;

import com.example.library_online.dto.book.BookAddRequest;
import com.example.library_online.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @PostMapping("/add")
    public void addBook(@RequestBody BookAddRequest request, @RequestHeader("Authorization") String token){
        bookService.addBook(request, token);
    }
}
