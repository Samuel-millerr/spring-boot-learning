package com.springbootlearning.quickstart.dao;

import com.springbootlearning.quickstart.domain.Author;
import com.springbootlearning.quickstart.domain.Book;

public class TestDataUtil {

    private TestDataUtil() {
    }

    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("John")
                .age(80)
                .build();
    }

    public static Book createTestBook() {
        return Book.builder()
                .isbn("978-1-2345-678-9")
                .title("The Shadow in the Attic")
                .authorId(1L)
                .build();
    }
}
