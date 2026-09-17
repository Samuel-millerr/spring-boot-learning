package com.springbootlearning.quickstart.dao;

import com.springbootlearning.quickstart.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDAO {

    void create(Author author);

    Optional<Author> findOne(Long id);

    List<Author> find();
}
