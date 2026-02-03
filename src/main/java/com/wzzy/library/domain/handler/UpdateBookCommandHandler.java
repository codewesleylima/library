package com.wzzy.library.domain.handler;

import com.wzzy.library.domain.command.UpdateBookCommand;
import com.wzzy.library.domain.model.Book;
import com.wzzy.library.infrastructure.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateBookCommandHandler {

    @Autowired
    private BookRepository bookRepository;

    public Book handle(UpdateBookCommand command) {
        Book book = bookRepository.findById(command.getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(command.getTitle());
        book.setAuthor(command.getAuthor());
        book.setIsbn(command.getIsbn());
        return bookRepository.save(book);
    }
}