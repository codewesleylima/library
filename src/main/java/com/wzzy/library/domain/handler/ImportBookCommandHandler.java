package com.wzzy.library.domain.handler;

import com.wzzy.library.domain.command.ImportBookCommand;
import com.wzzy.library.domain.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImportBookCommandHandler {

    @Autowired
    private CreateBookCommandHandler createBookCommandHandler;

    public Book handle(ImportBookCommand command) {
        // Reuse the existing create handler
        return createBookCommandHandler.handle(
            new com.wzzy.library.domain.command.CreateBookCommand(
                command.getTitle(),
                command.getAuthor(),
                command.getIsbn()
            )
        );
    }
}