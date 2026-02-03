package com.wzzy.library.domain.handler;

import com.wzzy.library.domain.model.Book;
import com.wzzy.library.domain.query.SearchExternalBooksQuery;
import com.wzzy.library.infrastructure.external.OpenLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class SearchExternalBooksQueryHandler {

    @Autowired
    private OpenLibraryService openLibraryService;

    public Mono<List<Book>> handle(SearchExternalBooksQuery query) {
        return openLibraryService.searchBooks(query.getQuery());
    }
}