package com.springbootlearning.quickstart.mappers.impl;

import com.springbootlearning.quickstart.domain.dto.BookDTO;
import com.springbootlearning.quickstart.domain.entities.Book;
import com.springbootlearning.quickstart.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements Mapper<Book, BookDTO> {

    private ModelMapper modelMapper;

    public BookMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDTO mapTo(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public Book mapFrom(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

}
