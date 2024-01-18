package com.example.library_online.controller;

import com.example.library_online.dto.book.BookAddRequest;
import com.example.library_online.dto.book.BookResponse;
import com.example.library_online.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @PostMapping("/add")
    public void addBook(@RequestBody BookAddRequest request, @RequestHeader("Authorization") String token){
        bookService.addBook(request, token);
    }
    @GetMapping("/books")
    public List<BookResponse> bookResponses(@RequestHeader("Authorization") String string){
        return bookService.getAll(string);
    }


    @PostMapping("/buy/{bookId}")
    public void buy(@PathVariable Long bookId, @RequestHeader("Authorization") String token){
        bookService.buy(bookId, token);
    }
    @GetMapping("/my/books")
    public List<BookResponse> myBooks(@RequestHeader("Authorization") String string){
        return bookService.getMyBooks(string);
    }
}
