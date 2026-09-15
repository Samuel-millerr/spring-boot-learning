package com.springbootlearning.quickstart.dao.impl;

import com.springbootlearning.quickstart.dao.TestDataUtil;
import com.springbootlearning.quickstart.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDAOImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDAOImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSQL() {
        Book book = TestDataUtil.createTestBook();

        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("978-1-2345-678-9"), eq("The Shadow in the Attic"), eq(1L)
        );
    }

    @Test
    public void testThatFindOneGeneratesTheCorrectSQL() {
        underTest.findOne("978-1-2345-678-9");

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDAOImpl.BookRowMapper>any(),
                eq("978-1-2345-678-9")
        );
    }
}
