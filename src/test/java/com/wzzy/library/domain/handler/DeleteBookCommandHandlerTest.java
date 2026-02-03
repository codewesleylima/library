package com.wzzy.library.domain.handler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wzzy.library.domain.command.DeleteBookCommand;
import com.wzzy.library.infrastructure.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class DeleteBookCommandHandlerTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private DeleteBookCommandHandler handler;

    @Test
    void shouldDeleteBookSuccessfully() {
        // Given
        Long bookId = 1L;
        DeleteBookCommand command = new DeleteBookCommand(bookId);

        // When
        handler.handle(command);

        // Then
        verify(bookRepository).deleteById(bookId);
    }
}