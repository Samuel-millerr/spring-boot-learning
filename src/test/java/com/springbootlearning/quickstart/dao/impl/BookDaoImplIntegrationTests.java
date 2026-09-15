package com.springbootlearning.quickstart.dao.impl;

import com.springbootlearning.quickstart.dao.AuthorDAO;
import com.springbootlearning.quickstart.dao.TestDataUtil;
import com.springbootlearning.quickstart.domain.Author;
import com.springbootlearning.quickstart.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookDaoImplIntegrationTests {

    private BookDAOImpl underTest;

    private AuthorDAO authorDAO;

    @Autowired
    public BookDaoImplIntegrationTests(BookDAOImpl underTest, AuthorDAO authorDAO) {
        this.underTest = underTest;
        this.authorDAO = authorDAO;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthor();

        if (authorDAO.findOne(author.getId()).isEmpty()) {
            authorDAO.create(author);
        }

        Book book = TestDataUtil.createTestBook();
        book.setAuthorId(author.getId());

        underTest.create(book);
        Optional<Book> result = underTest.findOne(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }
}
