package com.springbootlearning.quickstart.services.impl;

import com.springbootlearning.quickstart.domain.entities.Book;
import com.springbootlearning.quickstart.repositories.BookRepository;
import com.springbootlearning.quickstart.services.BookService;
import org.springframework.stereotype.Service;

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
}
