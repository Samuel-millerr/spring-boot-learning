package com.springbootlearning.quickstart.controllers;

import com.springbootlearning.quickstart.TestDataUtil;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    public void testThatCreateAuthorReturnsHttp201Created() throws Exception {
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
    public void testThatCreateAuthorReturnsSavedAuthor() throws Exception {
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
    public void testThatListAuthorsReturnsHttp200Ok() throws Exception {
        mockMvc.perform(
                get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void testThatListAuthorsReturnsAuthors() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        author.setId(null);
        authorService.save(author);

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
    public void testThatGetAuthorReturnsHttp200OkWhenAuthorExists() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        author.setId(null);
        Author savedAuthor = authorService.save(author);

        mockMvc.perform(
                get("/authors/{id}", savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void testThatGetAuthorReturnsHttp404NotFoundWhenAuthorDoesNotExist() throws Exception {
        mockMvc.perform(
                get("/authors/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }

    @Test
    public void testThatGetAuthorReturnsAuthorWhenAuthorExists() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        author.setId(null);
        Author savedAuthor = authorService.save(author);

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

    @Test
    public void testThatFullUpdateAuthorReturnsHttp404NotFoundWhenAuthorDoesNotExist() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        String requestBody = objectMapper.writeValueAsString(author);

        mockMvc.perform(
                put("/authors/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(status().isNotFound());
    }

    @Test
    public void testThatFullUpdateAuthorReturnsHttp200OkWhenAuthorExists() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        author.setId(null);
        Author savedAuthor = authorService.save(author);

        Author updateAuthor = TestDataUtil.createTestAuthorB();
        String requestBody = objectMapper.writeValueAsString(updateAuthor);

        mockMvc.perform(
                put("/authors/{id}", savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(status().isOk());
    }

    @Test
    public void testThatFullUpdateAuthorReturnsUpdatedAuthorWhenAuthorExists() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        author.setId(null);
        Author savedAuthor = authorService.save(author);

        Author updateAuthor = TestDataUtil.createTestAuthorB();
        updateAuthor.setId(savedAuthor.getId());
        String requestBody = objectMapper.writeValueAsString(updateAuthor);

        mockMvc.perform(
                put("/authors/{id}", savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(
                jsonPath("$.id").value(savedAuthor.getId())
        ).andExpect(
                jsonPath("$.name").value(updateAuthor.getName())
        ).andExpect(
                jsonPath("$.age").value(updateAuthor.getAge())
        );
    }

    @Test
    public void testThatPartialUpdateAuthorReturnsHttp200OkWhenAuthorExists() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        author.setId(null);
        Author savedAuthor = authorService.save(author);

        Author updateAuthor = TestDataUtil.createTestAuthorA();
        updateAuthor.setName("UPDATED");
        String requestBody = objectMapper.writeValueAsString(updateAuthor);

        mockMvc.perform(
                patch("/authors/{id}", savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(status().isOk());
    }

    @Test
    public void testThatPartialUpdateAuthorReturnsUpdatedAuthorWhenAuthorExists() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        author.setId(null);
        Author savedAuthor = authorService.save(author);

        Author updateAuthor = TestDataUtil.createTestAuthorA();
        updateAuthor.setName("UPDATED");
        String requestBody = objectMapper.writeValueAsString(updateAuthor);

        mockMvc.perform(
                patch("/authors/" + savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(
                jsonPath("$.id").value(savedAuthor.getId())
        ).andExpect(
                jsonPath("$.name").value(updateAuthor.getName())
        ).andExpect(
                jsonPath("$.age").value(updateAuthor.getAge())
        );
    }

    @Test
    public void testThatDeleteAuthorReturnsHttp204NoContentWhenAuthorDoesNotExist() throws Exception {
        mockMvc.perform(
                delete("/authors/999")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }

    @Test
    public void testThatDeleteAuthorReturnsHttp204NoContentWhenAuthorExists() throws Exception {
        Author author = TestDataUtil.createTestAuthorA();
        author.setId(null);
        Author savedAuthor = authorService.save(author);

        mockMvc.perform(
                delete("/authors/" + savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }
}
