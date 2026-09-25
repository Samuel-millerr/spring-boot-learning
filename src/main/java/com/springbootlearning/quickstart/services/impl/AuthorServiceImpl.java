package com.springbootlearning.quickstart.services.impl;

import com.springbootlearning.quickstart.domain.entities.Author;
import com.springbootlearning.quickstart.repositories.AuthorRepository;
import com.springbootlearning.quickstart.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @Override
    public List<Author> findAll() {
        return StreamSupport.stream(
                authorRepository.findAll().spliterator(), false
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<Author> findOne(Long id) {
        return authorRepository.findById(id);
    }
}
