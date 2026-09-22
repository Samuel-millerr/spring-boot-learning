package com.springbootlearning.quickstart.services;

import com.springbootlearning.quickstart.domain.entities.Book;

public interface BookService {

    Book createBook(String isbn, Book book);

}
