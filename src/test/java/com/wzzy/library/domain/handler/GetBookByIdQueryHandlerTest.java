package com.wzzy.library.domain.handler;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wzzy.library.domain.model.Book;
import com.wzzy.library.domain.query.GetBookByIdQuery;
import com.wzzy.library.infrastructure.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class GetBookByIdQueryHandlerTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private GetBookByIdQueryHandler handler;

    @Test
    void shouldReturnBookWhenFound() {
        // Given
        Long bookId = 1L;
        Book expectedBook = new Book();
        expectedBook.setId(bookId);
        expectedBook.setTitle("Test Book");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(expectedBook));

        // When
        Optional<Book> result = handler.handle(new GetBookByIdQuery(bookId));

        // Then
        assertTrue(result.isPresent());
        assertEquals("Test Book", result.get().getTitle());
    }

    @Test
    void shouldReturnEmptyWhenBookNotFound() {
        // Given
        Long bookId = 1L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // When
        Optional<Book> result = handler.handle(new GetBookByIdQuery(bookId));

        // Then
        assertFalse(result.isPresent());
    }
}