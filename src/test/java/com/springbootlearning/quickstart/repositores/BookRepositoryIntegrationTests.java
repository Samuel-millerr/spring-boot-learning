package com.springbootlearning.quickstart.repositores;

import com.springbootlearning.quickstart.TestDataUtil;
import com.springbootlearning.quickstart.domain.Author;
import com.springbootlearning.quickstart.domain.Book;
import com.springbootlearning.quickstart.repositories.BookRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTests {

    BookRepository underTest;

    @Autowired
    public BookRepositoryIntegrationTests(BookRepository underTest) {
        this.underTest = underTest;
    }

    private static Book book;

    @Test
    public void bookRepositorySimpleTest() {
        Book book = TestDataUtil.createTestBookA();

        underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);

        underTest.delete(book);
        result = underTest.findById(book.getIsbn());

        assertThat(result).isNotPresent();
    }
}
