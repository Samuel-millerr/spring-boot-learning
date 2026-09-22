package com.springbootlearning.quickstart.mappers.impl;

import com.springbootlearning.quickstart.domain.dto.AuthorDTO;
import com.springbootlearning.quickstart.domain.entities.Author;
import com.springbootlearning.quickstart.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperImpl implements Mapper<Author, AuthorDTO> {

    private ModelMapper modelMapper;

    public AuthorMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDTO mapTo(Author author) {
        return modelMapper.map(author, AuthorDTO.class);
    }

    @Override
    public Author mapFrom(AuthorDTO authorDTO) {
        return modelMapper.map(authorDTO, Author.class);
    }

}
