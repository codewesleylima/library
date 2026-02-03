package com.wzzy.library;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wzzy.library.domain.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BookIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateAndRetrieveBook() throws Exception {
        // Load JSON request
        String createRequestJson = new String(Files.readAllBytes(Paths.get("src/test/resources/create-book-request.json")));

        // Create book
        MvcResult createResult = mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createRequestJson))
                .andExpect(status().isOk())
                .andReturn();

        Book createdBook = objectMapper.readValue(createResult.getResponse().getContentAsString(), Book.class);
        assertNotNull(createdBook.getId());
        assertEquals("Integration Test Book", createdBook.getTitle());

        // Retrieve book
        mockMvc.perform(get("/api/books/" + createdBook.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Integration Test Book"))
                .andExpect(jsonPath("$.author").value("Test Author"))
                .andExpect(jsonPath("$.isbn").value("1234567890123"));
    }

    @Test
    void shouldUpdateBook() throws Exception {
        // First create a book
        String createRequestJson = new String(Files.readAllBytes(Paths.get("src/test/resources/create-book-request.json")));

        MvcResult createResult = mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createRequestJson))
                .andExpect(status().isOk())
                .andReturn();

        Book createdBook = objectMapper.readValue(createResult.getResponse().getContentAsString(), Book.class);

        // Load update JSON
        String updateRequestJson = new String(Files.readAllBytes(Paths.get("src/test/resources/update-book-request.json")));

        // Update book
        mockMvc.perform(put("/api/books/" + createdBook.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateRequestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Integration Test Book"))
                .andExpect(jsonPath("$.author").value("Updated Test Author"))
                .andExpect(jsonPath("$.isbn").value("9876543210987"));
    }

    @Test
    void shouldDeleteBook() throws Exception {
        // Create book
        String createRequestJson = new String(Files.readAllBytes(Paths.get("src/test/resources/create-book-request.json")));

        MvcResult createResult = mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createRequestJson))
                .andExpect(status().isOk())
                .andReturn();

        Book createdBook = objectMapper.readValue(createResult.getResponse().getContentAsString(), Book.class);

        // Delete book
        mockMvc.perform(delete("/api/books/" + createdBook.getId()))
                .andExpect(status().isNoContent());

        // Verify book is deleted
        mockMvc.perform(get("/api/books/" + createdBook.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetAllBooks() throws Exception {
        // Create multiple books
        String createRequestJson = new String(Files.readAllBytes(Paths.get("src/test/resources/create-book-request.json")));

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createRequestJson))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createRequestJson.replace("Integration Test Book", "Second Book")))
                .andExpect(status().isOk());

        // Get all books
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}