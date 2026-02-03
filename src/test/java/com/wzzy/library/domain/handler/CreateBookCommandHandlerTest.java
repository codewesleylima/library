package com.wzzy.library.domain.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wzzy.library.domain.command.CreateBookCommand;
import com.wzzy.library.domain.model.Book;
import com.wzzy.library.infrastructure.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class CreateBookCommandHandlerTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private CreateBookCommandHandler handler;

    @Test
    void shouldCreateBookSuccessfully() {
        // Given
        CreateBookCommand command = new CreateBookCommand("Test Title", "Test Author", "1234567890");
        Book expectedBook = new Book();
        expectedBook.setId(1L);
        expectedBook.setTitle("Test Title");
        expectedBook.setAuthor("Test Author");
        expectedBook.setIsbn("1234567890");

        when(bookRepository.save(any(Book.class))).thenReturn(expectedBook);

        // When
        Book result = handler.handle(command);

        // Then
        assertNotNull(result);
        assertEquals("Test Title", result.getTitle());
        assertEquals("Test Author", result.getAuthor());
        assertEquals("1234567890", result.getIsbn());
    }
}