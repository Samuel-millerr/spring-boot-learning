package com.springbootlearning.quickstart.controllers;

import com.springbootlearning.quickstart.domain.dto.BookDTO;
import com.springbootlearning.quickstart.domain.entities.Book;
import com.springbootlearning.quickstart.mappers.Mapper;
import com.springbootlearning.quickstart.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private Mapper<Book, BookDTO> bookMapper;
    private BookService bookService;

    public BookController(Mapper<Book, BookDTO> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDTO> createUpdateBook(@PathVariable String isbn, @RequestBody BookDTO request) {
        Book book = bookMapper.mapFrom(request);
        boolean bookExists = bookService.isExists(isbn);
        Book savedBook = bookService.createUpdateBook(isbn, book);
        BookDTO response = bookMapper.mapTo(savedBook);

        if (bookExists) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    @GetMapping("/books")
    public List<BookDTO> listBooks() {
        List<Book> books = bookService.findAll();
        return books.stream().map(bookMapper::mapTo).toList();
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<BookDTO> getBook(@PathVariable String isbn) {
        Optional<Book> foundBook = bookService.findOne(isbn);
        return foundBook.map(book -> {
                    BookDTO response = bookMapper.mapTo(book);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/books/{isbn}")
    public ResponseEntity<BookDTO> partialUpdate(@PathVariable String isbn, @RequestBody BookDTO request) {
        if (!bookService.isExists(isbn)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Book book = bookMapper.mapFrom(request);
        Book updatedBook = bookService.partialUpdate(isbn, book);

        return new ResponseEntity<>(bookMapper.mapTo(updatedBook), HttpStatus.OK);
    }

    @DeleteMapping("/books/{isbn}")
    public ResponseEntity deleteBook(@PathVariable String isbn) {
        bookService.delete(isbn);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
