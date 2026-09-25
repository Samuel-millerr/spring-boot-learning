package com.springbootlearning.quickstart.services.impl;

import com.springbootlearning.quickstart.domain.entities.Book;
import com.springbootlearning.quickstart.repositories.BookRepository;
import com.springbootlearning.quickstart.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(String isbn, Book book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return StreamSupport.stream(
                bookRepository.findAll().spliterator(), false
        ).toList(
        );
    }

    @Override
    public Optional<Book> findOne(String isbn) {
        return bookRepository.findById(isbn);
    }
}
