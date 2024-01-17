package com.example.library_online.service.impl;

import com.example.library_online.dto.book.BookAddRequest;
import com.example.library_online.entities.Book;
import com.example.library_online.entities.Type;
import com.example.library_online.enums.Role;
import com.example.library_online.exception.BadCredentialsException;
import com.example.library_online.exception.NotFoundException;
import com.example.library_online.repositories.BookRepository;
import com.example.library_online.repositories.TypeRepository;
import com.example.library_online.service.AuthService;
import com.example.library_online.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final AuthService authService;
    private final TypeRepository typeRepository;

    private final BookRepository bookRepository;
    @Override
    public void addBook(BookAddRequest request, String token) {
        if (bookRepository.findByTranscript(request.getTranscript()).isPresent())
            throw new NotFoundException("book with this transcript is already exist!: "+request.getTranscript(),
                    HttpStatus.BAD_REQUEST);
        if (!authService.getUsernameFromToken(token).getRole().equals(Role.ADMIN))
            throw new BadCredentialsException("this function only for admin!");

        Book book = new Book();

        book.setName(request.getName());
        book.setPrize(request.getPrize());
        book.setAge_access(request.getAge_access());
        book.setAuthor_full_name(request.getAuthor_full_name());
        book.setTranscript(request.getTranscript());
        book.setCreated_date(LocalDateTime.now().toString());
        book.setExist(true);
        Optional<Type> type = typeRepository.findByName(request.getType());
        if (type.isEmpty())
            throw new NotFoundException("sjhbf", HttpStatus.BAD_REQUEST);
        book.setType(type.get());
        bookRepository.save(book);
    }
}
