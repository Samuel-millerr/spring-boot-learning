package com.springbootlearning.quickstart.dao.impl;

import com.springbootlearning.quickstart.dao.AuthorDAO;
import com.springbootlearning.quickstart.dao.TestDataUtil;
import com.springbootlearning.quickstart.domain.Author;
import com.springbootlearning.quickstart.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDAOImplIntegrationTests {

    private BookDAOImpl underTest;

    private AuthorDAO authorDAO;

    @Autowired
    public BookDAOImplIntegrationTests(BookDAOImpl underTest, AuthorDAO authorDAO) {
        this.underTest = underTest;
        this.authorDAO = authorDAO;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthorA();

        if (authorDAO.findOne(author.getId()).isEmpty()) {
            authorDAO.create(author);
        }

        Book book = TestDataUtil.createTestBookA();
        book.setAuthorId(author.getId());
        underTest.create(book);

        Optional<Book> result = underTest.findOne(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreateAndRecalled() {
        Author author = TestDataUtil.createTestAuthorA();

        if (authorDAO.findOne(author.getId()).isEmpty()) {
            authorDAO.create(author);
        }

        Book bookA = TestDataUtil.createTestBookA();
        bookA.setAuthorId(author.getId());
        underTest.create(bookA);

        Book bookB = TestDataUtil.createTestBookB();
        bookB.setAuthorId(author.getId());
        underTest.create(bookB);

        Book bookC = TestDataUtil.createTestBookC();
        bookC.setAuthorId(author.getId());
        underTest.create(bookC);

        List<Book> result = underTest.find();

        assertThat(result)
                .hasSize(3)
                .containsExactly(bookA, bookB, bookC);
    }

    @Test
    public void testThatBookCanBeUpdated() {
        Author author = TestDataUtil.createTestAuthorA();

        if (authorDAO.findOne(author.getId()).isEmpty()) {
            authorDAO.create(author);
        }

        Book book = TestDataUtil.createTestBookA();
        book.setAuthorId(author.getId());
        underTest.create(book);

        book.setTitle("UPDATED");
        underTest.update(book.getIsbn(), book);

        Optional<Book> result = underTest.findOne(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }
}
