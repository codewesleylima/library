package com.wzzy.library.domain.handler;

import com.wzzy.library.domain.model.Book;
import com.wzzy.library.domain.query.GetBookByIdQuery;
import com.wzzy.library.infrastructure.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetBookByIdQueryHandler {

    @Autowired
    private BookRepository bookRepository;

    public Optional<Book> handle(GetBookByIdQuery query) {
        return bookRepository.findById(query.getId());
    }
}