package com.wzzy.library.application.service;

import com.wzzy.library.domain.command.CreateBookCommand;
import com.wzzy.library.domain.command.DeleteBookCommand;
import com.wzzy.library.domain.command.UpdateBookCommand;
import com.wzzy.library.domain.handler.*;
import com.wzzy.library.domain.model.Book;
import com.wzzy.library.domain.query.GetAllBooksQuery;
import com.wzzy.library.domain.query.GetBookByIdQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationService {

    @Autowired
    private CreateBookCommandHandler createBookCommandHandler;

    @Autowired
    private UpdateBookCommandHandler updateBookCommandHandler;

    @Autowired
    private DeleteBookCommandHandler deleteBookCommandHandler;

    @Autowired
    private GetAllBooksQueryHandler getAllBooksQueryHandler;

    @Autowired
    private GetBookByIdQueryHandler getBookByIdQueryHandler;

    public Book createBook(CreateBookCommand command) {
        return createBookCommandHandler.handle(command);
    }

    public Book updateBook(UpdateBookCommand command) {
        return updateBookCommandHandler.handle(command);
    }

    public void deleteBook(DeleteBookCommand command) {
        deleteBookCommandHandler.handle(command);
    }

    public List<Book> getAllBooks() {
        return getAllBooksQueryHandler.handle(new GetAllBooksQuery());
    }

    public Optional<Book> getBookById(Long id) {
        return getBookByIdQueryHandler.handle(new GetBookByIdQuery(id));
    }
}