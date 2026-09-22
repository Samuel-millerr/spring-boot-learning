package com.springbootlearning.quickstart.services;

import com.springbootlearning.quickstart.domain.entities.Book;

import java.util.List;

public interface BookService {

    Book createBook(String isbn, Book book);

    List<Book> findAll();

}
