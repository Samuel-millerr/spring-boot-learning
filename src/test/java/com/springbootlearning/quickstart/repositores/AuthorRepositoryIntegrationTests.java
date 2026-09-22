package com.springbootlearning.quickstart.repositores;

import com.springbootlearning.quickstart.TestDataUtil;
import com.springbootlearning.quickstart.domain.Author;
import com.springbootlearning.quickstart.repositories.AuthorRepository;
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
public class AuthorRepositoryIntegrationTests {

    private AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository underTest) {
        this.underTest = underTest;
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
}
