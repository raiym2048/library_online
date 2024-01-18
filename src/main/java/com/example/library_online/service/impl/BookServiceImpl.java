package com.example.library_online.service.impl;

import com.example.library_online.dto.book.BookAddRequest;
import com.example.library_online.dto.book.BookResponse;
import com.example.library_online.entities.Book;
import com.example.library_online.entities.Type;
import com.example.library_online.entities.User;
import com.example.library_online.enums.Role;
import com.example.library_online.exception.BadCredentialsException;
import com.example.library_online.exception.NotFoundException;
import com.example.library_online.mapper.BookMapper;
import com.example.library_online.repositories.BookRepository;
import com.example.library_online.repositories.TypeRepository;
import com.example.library_online.repositories.UserRepository;
import com.example.library_online.service.AuthService;
import com.example.library_online.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final AuthService authService;
    private final TypeRepository typeRepository;
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

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
        book.setAgeAccess(request.getAge_access());
        book.setAuthor_full_name(request.getAuthor_full_name());
        book.setTranscript(request.getTranscript());
        book.setCreated_date(LocalDateTime.now().toString());
        book.setExist(true);
        Optional<Type> type = typeRepository.findByName(request.getType());
        if (type.isEmpty())
            throw new NotFoundException("no type with name: "+request.getType(), HttpStatus.BAD_REQUEST);
        book.setType(type.get());
        bookRepository.save(book);
    }

    @Override
    public List<BookResponse> getAll(String s) {
        User user = authService.getUsernameFromToken(s);

        if (!user.getRole().equals(Role.ADMIN)){
            System.out.println("the user");
            List<BookResponse> bookResponses = bookMapper.toDtoS(bookRepository.findAllByExistAndAgeAccessLessThan(
                    true, user.getCustomer().getAge()));
            System.out.println("the size: "+bookResponses.size());
            return bookResponses;
        }
        System.out.println("the admin");

        return bookMapper.toDtoS(bookRepository.findAll());
    }

    @Override
    public void buy(Long bookId, String token) {
        User user = authService.getUsernameFromToken(token);
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty())
            throw new NotFoundException("this book sold", HttpStatus.BAD_REQUEST);
        book.get().setExist(false);
        List<Book> books = new ArrayList<>();
        if (!user.getCustomer().getBooks().isEmpty())
            books = user.getCustomer().getBooks();
        books.add(book.get());
        user.getCustomer().setBooks(books);
        userRepository.save(user);
    }

    @Override
    public List<BookResponse> getMyBooks(String string) {
        User user = authService.getUsernameFromToken(string);

        if (!user.getRole().equals(Role.ADMIN)){
            System.out.println("the user");
            List<BookResponse> bookResponses = bookMapper.toDtoS(user.getCustomer().getBooks());
            System.out.println("the size: "+bookResponses.size());
            return bookResponses;
        }
        return null;
    }
}
