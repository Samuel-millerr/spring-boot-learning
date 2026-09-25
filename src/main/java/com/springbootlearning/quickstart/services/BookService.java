package com.springbootlearning.quickstart.services;

import com.springbootlearning.quickstart.domain.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book createUpdateBook(String isbn, Book book);

    List<Book> findAll();

    Optional<Book> findOne(String isbn);

    boolean isExists(String isbn);

}
