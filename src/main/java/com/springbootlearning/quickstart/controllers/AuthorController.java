package com.springbootlearning.quickstart.controllers;

import com.springbootlearning.quickstart.domain.dto.AuthorDTO;
import com.springbootlearning.quickstart.domain.entities.Author;
import com.springbootlearning.quickstart.mappers.Mapper;
import com.springbootlearning.quickstart.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

    private AuthorService authorService;
    private Mapper<Author, AuthorDTO> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<Author, AuthorDTO> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping("/authors")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO request) {
        Author author = authorMapper.mapFrom(request);
        Author savedAuthor = authorService.createAuthor(author);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthor), HttpStatus.CREATED);
    }

    @GetMapping("/authors")
    public List<AuthorDTO> listAuthors() {
        List<Author> authors = authorService.findAll();
       return authors.stream().map(authorMapper::mapTo).toList();
    }

}
