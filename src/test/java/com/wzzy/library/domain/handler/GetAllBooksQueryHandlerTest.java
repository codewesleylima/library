package com.wzzy.library.domain.handler;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wzzy.library.domain.model.Book;
import com.wzzy.library.domain.query.GetAllBooksQuery;
import com.wzzy.library.infrastructure.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class GetAllBooksQueryHandlerTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private GetAllBooksQueryHandler handler;

    @Test
    void shouldReturnAllBooks() {
        // Given
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Book 1");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Book 2");

        List<Book> expectedBooks = Arrays.asList(book1, book2);
        when(bookRepository.findAll()).thenReturn(expectedBooks);

        // When
        List<Book> result = handler.handle(new GetAllBooksQuery());

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Book 1", result.get(0).getTitle());
        assertEquals("Book 2", result.get(1).getTitle());
    }
}