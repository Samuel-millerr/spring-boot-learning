package com.springbootlearning.quickstart.services.impl;

import com.springbootlearning.quickstart.domain.entities.Author;
import com.springbootlearning.quickstart.repositories.AuthorRepository;
import com.springbootlearning.quickstart.services.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }
}
