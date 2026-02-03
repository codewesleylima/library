package com.wzzy.library.domain.handler;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wzzy.library.domain.command.UpdateBookCommand;
import com.wzzy.library.domain.model.Book;
import com.wzzy.library.infrastructure.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class UpdateBookCommandHandlerTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private UpdateBookCommandHandler handler;

    @Test
    void shouldUpdateBookSuccessfully() {
        // Given
        Long bookId = 1L;
        UpdateBookCommand command = new UpdateBookCommand(bookId, "Updated Title", "Updated Author", "0987654321");

        Book existingBook = new Book();
        existingBook.setId(bookId);
        existingBook.setTitle("Old Title");
        existingBook.setAuthor("Old Author");
        existingBook.setIsbn("1234567890");

        Book updatedBook = new Book();
        updatedBook.setId(bookId);
        updatedBook.setTitle("Updated Title");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setIsbn("0987654321");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(existingBook)).thenReturn(updatedBook);

        // When
        Book result = handler.handle(command);

        // Then
        assertNotNull(result);
        assertEquals("Updated Title", result.getTitle());
        assertEquals("Updated Author", result.getAuthor());
        assertEquals("0987654321", result.getIsbn());
    }

    @Test
    void shouldThrowExceptionWhenBookNotFound() {
        // Given
        Long bookId = 1L;
        UpdateBookCommand command = new UpdateBookCommand(bookId, "Title", "Author", "ISBN");

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> handler.handle(command));
        assertEquals("Book not found", exception.getMessage());
    }
}