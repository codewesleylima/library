package com.wzzy.library.domain.handler;

import com.wzzy.library.domain.command.CreateBookCommand;
import com.wzzy.library.domain.model.Book;
import com.wzzy.library.infrastructure.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateBookCommandHandler {

    @Autowired
    private BookRepository bookRepository;

    public Book handle(CreateBookCommand command) {
        Book book = new Book();
        book.setTitle(command.getTitle());
        book.setAuthor(command.getAuthor());
        book.setIsbn(command.getIsbn());
        return bookRepository.save(book);
    }
}