package com.springbootlearning.quickstart.mappers;

import com.springbootlearning.quickstart.TestDataUtil;
import com.springbootlearning.quickstart.domain.Book;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonTests {

    @Test
    public void testThatObjectMapperCanCreateJsonFromJavaObject() {
        ObjectMapper objectMapper = new ObjectMapper();

        Book book = TestDataUtil.createTestBookA();

        String result = objectMapper.writeValueAsString(book);

        assertThat(result).isEqualTo("{\"isbn\":\"978-1-2345-678-9\",\"title\":\"The Shadow in the Attic\",\"authorId\":1}");
    }

    @Test
    public void testThatObjectMapperCanCreateJavaObjectFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = "{\"isbn\":\"978-1-2345-678-9\",\"title\":\"The Shadow in the Attic\",\"authorId\":1}";

        Book result = objectMapper.readValue(json, Book.class);

        assertThat(result).isEqualTo(TestDataUtil.createTestBookA());
    }
}
