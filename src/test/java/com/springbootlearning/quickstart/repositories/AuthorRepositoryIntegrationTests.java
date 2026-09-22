package com.springbootlearning.quickstart.repositories;

import com.springbootlearning.quickstart.TestDataUtil;
import com.springbootlearning.quickstart.domain.entities.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
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
public class AuthorRepositoryIntegrationTests {

    private AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @BeforeEach
    public void cleanUp() {
        underTest.deleteAll();
    }

    @Test
    public void authorRepositorySimpleTest() {
        Author author = TestDataUtil.createTestAuthorA();
        author.setId(null);

        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);

        underTest.delete(author);
        result = underTest.findById(author.getId());

        assertThat(result).isNotPresent();
    }

    @Test
    public void testThatGetAuthorWithAgeGreaterThan() {
        Author authorA = TestDataUtil.createTestAuthorA();
        authorA.setId(null);
        underTest.save(authorA);

        Author authorB = TestDataUtil.createTestAuthorB();
        authorB.setId(null);
        underTest.save(authorB);

        Author authorC = TestDataUtil.createTestAuthorC();
        authorC.setId(null);
        underTest.save(authorC);

        Iterable<Author> result = underTest.findAuthorWithAgeGreaterThan(50);
        assertThat(result).containsExactlyInAnyOrder(authorA);
    }

    @Test
    public void testThatGetAuthorWithAgeLessThan() {
        Author authorA = TestDataUtil.createTestAuthorA();
        authorA.setId(null);
        underTest.save(authorA);

        Author authorB = TestDataUtil.createTestAuthorB();
        authorB.setId(null);
        underTest.save(authorB);

        Author authorC = TestDataUtil.createTestAuthorC();
        authorC.setId(null);
        underTest.save(authorC);

        Iterable<Author> result = underTest.ageLessThan(50);
        assertThat(result).containsExactlyInAnyOrder(authorB, authorC);
    }
}
