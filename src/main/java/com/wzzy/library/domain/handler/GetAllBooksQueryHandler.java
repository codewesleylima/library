package com.wzzy.library.domain.handler;

import com.wzzy.library.domain.model.Book;
import com.wzzy.library.domain.query.GetAllBooksQuery;
import com.wzzy.library.infrastructure.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllBooksQueryHandler {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> handle(GetAllBooksQuery query) {
        return bookRepository.findAll();
    }
}