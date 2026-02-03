package com.wzzy.library.infrastructure.external;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.wzzy.library.domain.model.Book;

import reactor.core.publisher.Mono;

@Service
public class OpenLibraryService {

    private final WebClient webClient;

    public OpenLibraryService() {
        this.webClient = WebClient.builder().baseUrl("https://openlibrary.org").build();
    }

    public Mono<List<Book>> searchBooks(String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search.json")
                        .queryParam("q", query)
                        .queryParam("limit", 10)
                        .build())
                .retrieve()
                .bodyToMono(OpenLibraryResponse.class)
                .map(response -> response.getDocs().stream()
                        .map(this::mapToBook)
                        .collect(Collectors.toList()));
    }

    private Book mapToBook(OpenLibraryBook openLibraryBook) {
        Book book = new Book();
        book.setTitle(openLibraryBook.getTitle());
        book.setAuthor(openLibraryBook.getAuthor_name() != null && !openLibraryBook.getAuthor_name().isEmpty()
                ? openLibraryBook.getAuthor_name().get(0) : "Unknown Author");
        book.setIsbn(openLibraryBook.getIsbn() != null && !openLibraryBook.getIsbn().isEmpty()
                ? openLibraryBook.getIsbn().get(0) : "No ISBN");
        return book;
    }

    // DTOs for Open Library API response
    public static class OpenLibraryResponse {
        private List<OpenLibraryBook> docs;

        public List<OpenLibraryBook> getDocs() {
            return docs;
        }

        public void setDocs(List<OpenLibraryBook> docs) {
            this.docs = docs;
        }
    }

    public static class OpenLibraryBook {
        private String title;
        private List<String> author_name;
        private List<String> isbn;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(List<String> author_name) {
            this.author_name = author_name;
        }

        public List<String> getIsbn() {
            return isbn;
        }

        public void setIsbn(List<String> isbn) {
            this.isbn = isbn;
        }
    }
}