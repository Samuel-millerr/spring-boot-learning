package com.springbootlearning.quickstart.controllers;

import com.springbootlearning.quickstart.TestDataUtil;
import com.springbootlearning.quickstart.domain.entities.Book;
import com.springbootlearning.quickstart.services.BookService;
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
public class BookControllerIntegrationTests {

    private MockMvc mockMvc;
    private BookService bookService;
    private ObjectMapper objectMapper;

    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, BookService bookService) {
        this.mockMvc = mockMvc;
        this.bookService = bookService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateBookReturnsHttp201Created() throws Exception {
        Book book = TestDataUtil.createTestBookA();

        String bookJson = objectMapper.writeValueAsString(book);

        mockMvc.perform(
                put("/books/{isbn}", book.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                status().isCreated()
        );
    }

    @Test
    public void testThatUpdateBookReturnsHttpStatus200Ok() throws Exception {
        Book book = TestDataUtil.createTestBookA();
        Book savedBook = bookService.createUpdateBook(book.getIsbn(), book);

        Book updatedBook = TestDataUtil.createTestBookB();
        String requestBody = objectMapper.writeValueAsString(updatedBook);

        mockMvc.perform(
                put("/books/{isbn}", savedBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(status().isOk());
    }

    @Test
    public void testThatCreateBookReturnsCreatedBook() throws Exception {
        Book book = TestDataUtil.createTestBookA();

        String bookJson = objectMapper.writeValueAsString(book);

        mockMvc.perform(
                put("/books/{isbn}", book.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                jsonPath("$.isbn").value(book.getIsbn())
        ).andExpect(
                jsonPath("$.title").value(book.getTitle())
        );
    }

    @Test
    public void testThatUpdateBookReturnsUpdatedBook() throws Exception {
        Book book = TestDataUtil.createTestBookA();
        Book savedBook = bookService.createUpdateBook(book.getIsbn(), book);

        Book updatedBook = TestDataUtil.createTestBookB();
        String requestBody = objectMapper.writeValueAsString(updatedBook);

        mockMvc.perform(
                put("/books/{isbn}", savedBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(
                jsonPath("$.isbn").value(savedBook.getIsbn())
        ).andExpect(
                jsonPath("$.title").value(updatedBook.getTitle())
        );
    }

    @Test
    public void testThatListBooksReturnsHttp200Ok() throws Exception {
        mockMvc.perform(
                get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    public void testThatListBooksReturnsListOfBooks() throws Exception {
        Book book = TestDataUtil.createTestBookA();
        bookService.createUpdateBook(book.getIsbn(), book);

        mockMvc.perform(
                get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                jsonPath("$[0].isbn").value(book.getIsbn())
        ).andExpect(
                jsonPath("$[0].title").value(book.getTitle())
        );

    }

    @Test
    public void testThatGetBookReturnsHttpStatus200OkWhenBookExist() throws Exception {
        Book book = TestDataUtil.createTestBookA();
        bookService.createUpdateBook(book.getIsbn(), book);

        mockMvc.perform(
                get("/books/{isbn}", book.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void testThatGetBookReturnsHttpStatus404NotFoundWhenBookDoesNotExist() throws Exception {
        mockMvc.perform(
                get("/books/{isbn}", "book-99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }

    @Test
    public void testThatGetBookReturnsBookWhenBookExist() throws Exception {
        Book book = TestDataUtil.createTestBookA();
        Book savedBook = bookService.createUpdateBook(book.getIsbn(), book);

        mockMvc.perform(
                        get("/books/{isbn}", savedBook.getIsbn())
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(
                        jsonPath("$.isbn").value(savedBook.getIsbn())
                )
                .andExpect(
                        jsonPath("$.title").value(book.getTitle())
                );
    }

    @Test
    public void testThatPartialUpdateBookReturnsHttpStatus200Ok() throws Exception {
        Book book = TestDataUtil.createTestBookA();
        Book savedBook = bookService.createUpdateBook(book.getIsbn(), book);

        Book updateBook = TestDataUtil.createTestBookA();
        updateBook.setTitle("UPDATED");
        String requestBody = objectMapper.writeValueAsString(updateBook);

        mockMvc.perform(
                patch("/books/{isbn}", savedBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(status().isOk());
    }

    @Test
    public void testThatPartialUpdateBookReturnsUpdatedBook() throws Exception {
        Book book = TestDataUtil.createTestBookA();
        Book savedBook = bookService.createUpdateBook(book.getIsbn(), book);

        Book updateBook = TestDataUtil.createTestBookA();
        updateBook.setTitle("UPDATED");
        String requestBody = objectMapper.writeValueAsString(updateBook);

        mockMvc.perform(
                patch("/books/{isbn}", savedBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(
                jsonPath("$.isbn").value(savedBook.getIsbn())
        ).andExpect(
                jsonPath("$.title").value(updateBook.getTitle())
        );
    }

    @Test
    public void testThatDeleteBookReturnsHttpStatus204ForNonExistingBook() throws Exception {
        mockMvc.perform(
                delete("/books/999")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }

    @Test
    public void testThatDeleteBookReturnsHttpStatus204ForExistingBook() throws Exception {
        Book book = TestDataUtil.createTestBookA();
        Book savedBook = bookService.createUpdateBook(book.getIsbn(), book);

        mockMvc.perform(
                delete("/books/" + savedBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }
}
