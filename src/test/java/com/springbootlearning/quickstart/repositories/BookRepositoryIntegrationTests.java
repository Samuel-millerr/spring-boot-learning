package com.springbootlearning.quickstart.repositories;

import com.springbootlearning.quickstart.TestDataUtil;
import com.springbootlearning.quickstart.domain.entities.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional(transactionManager = "jpaTransactionManager")
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
