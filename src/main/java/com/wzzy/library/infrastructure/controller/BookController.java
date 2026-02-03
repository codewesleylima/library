package com.wzzy.library.infrastructure.controller;

import com.wzzy.library.application.service.BookApplicationService;
import com.wzzy.library.domain.command.CreateBookCommand;
import com.wzzy.library.domain.command.DeleteBookCommand;
import com.wzzy.library.domain.command.UpdateBookCommand;
import com.wzzy.library.domain.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookApplicationService bookApplicationService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookApplicationService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookApplicationService.getBookById(id)
                .map(book -> ResponseEntity.ok(book))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Book createBook(@RequestBody CreateBookCommand command) {
        return bookApplicationService.createBook(command);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody UpdateBookCommand command) {
        command.setId(id);
        try {
            Book updatedBook = bookApplicationService.updateBook(command);
            return ResponseEntity.ok(updatedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        try {
            bookApplicationService.deleteBook(new DeleteBookCommand(id));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}