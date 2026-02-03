package com.wzzy.library.domain.handler;

import com.wzzy.library.domain.command.DeleteBookCommand;
import com.wzzy.library.infrastructure.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteBookCommandHandler {

    @Autowired
    private BookRepository bookRepository;

    public void handle(DeleteBookCommand command) {
        bookRepository.deleteById(command.getId());
    }
}