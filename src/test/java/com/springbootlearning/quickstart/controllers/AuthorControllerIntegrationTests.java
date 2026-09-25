package com.springbootlearning.quickstart.controllers;

import com.springbootlearning.quickstart.TestDataUtil;
import com.springbootlearning.quickstart.domain.dto.AuthorDTO;
import com.springbootlearning.quickstart.domain.entities.Author;
import com.springbootlearning.quickstart.services.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Transactional(transactionManager = "jpaTransactionManager")
public class AuthorControllerIntegrationTests {

    private MockMvc mockMvc;
    private AuthorService authorService;
    private ObjectMapper objectMapper;

    @Autowired
    public AuthorControllerIntegrationTests(MockMvc mockMvc, AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.authorService = authorService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsHttp201Created() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        author.setId(null);

        String authorJson = objectMapper.writeValueAsString(author);

        mockMvc.perform(
                post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                status().isCreated()
        );
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        author.setId(null);

        String authorJson = objectMapper.writeValueAsString(author);

        mockMvc.perform(
                        post("/authors")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(authorJson)
                ).andExpect(
                        jsonPath("$.id").isNumber()
                )
                .andExpect(
                        jsonPath("$.name").value(author.getName())
                )
                .andExpect(
                        jsonPath("$.age").value(author.getAge())
                );
    }

    @Test
    public void testThatListAuthorsReturnsHttpStatus200Ok() throws Exception {
        mockMvc.perform(
                get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void testThatListAuthorsReturnsListOfAuthors() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        author.setId(null);
        authorService.createAuthor(author);

        mockMvc.perform(
                        get("/authors")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(
                        jsonPath("$[0].id").isNumber()
                )
                .andExpect(
                        jsonPath("$[0].name").value(author.getName())
                )
                .andExpect(
                        jsonPath("$[0].age").value(author.getAge())
                );
    }

    @Test
    public void testThatGetAuthorReturnsHttpStatus200OkWhenAuthorExist() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        author.setId(null);
        authorService.createAuthor(author);

        mockMvc.perform(
                get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void testThatGetAuthorReturnsHttpStatus404NotFoundWhenAuthorDoesNotExist() throws Exception {
            mockMvc.perform(
                    get("/authors/99")
                            .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isNotFound());
    }

    @Test
    public void testThatGetAuthorReturnsAuthorWhenAuthorExist() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        author.setId(null);
        Author savedAuthor = authorService.createAuthor(author);

        mockMvc.perform(
                        get("/authors/{id}", savedAuthor.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(
                        jsonPath("$.id").value(savedAuthor.getId())
                )
                .andExpect(
                        jsonPath("$.name").value(author.getName())
                )
                .andExpect(
                        jsonPath("$.age").value(author.getAge())
                );
    }
}
