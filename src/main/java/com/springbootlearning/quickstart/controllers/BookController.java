package com.springbootlearning.quickstart.controllers;

import com.springbootlearning.quickstart.domain.dto.BookDTO;
import com.springbootlearning.quickstart.domain.entities.Book;
import com.springbootlearning.quickstart.mappers.Mapper;
import com.springbootlearning.quickstart.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private Mapper<Book, BookDTO> bookMapper;
    private BookService bookService;

    public BookController(Mapper<Book, BookDTO> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDTO> createBook(@PathVariable String isbn, @RequestBody BookDTO request) {
        Book book = bookMapper.mapFrom(request);
        Book savedBook = bookService.createBook(isbn, book);
        return new ResponseEntity<>(bookMapper.mapTo(savedBook), HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public List<BookDTO> listBooks() {
        List<Book> books = bookService.findAll();
        return books.stream().map(bookMapper::mapTo).toList();
    }
}
