package com.wzzy.library.application.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wzzy.library.domain.command.CreateBookCommand;
import com.wzzy.library.domain.command.DeleteBookCommand;
import com.wzzy.library.domain.command.UpdateBookCommand;
import com.wzzy.library.domain.handler.CreateBookCommandHandler;
import com.wzzy.library.domain.handler.DeleteBookCommandHandler;
import com.wzzy.library.domain.handler.GetAllBooksQueryHandler;
import com.wzzy.library.domain.handler.GetBookByIdQueryHandler;
import com.wzzy.library.domain.handler.UpdateBookCommandHandler;
import com.wzzy.library.domain.model.Book;
import com.wzzy.library.domain.query.GetAllBooksQuery;
import com.wzzy.library.domain.query.GetBookByIdQuery;

@ExtendWith(MockitoExtension.class)
class BookApplicationServiceTest {

    @Mock
    private CreateBookCommandHandler createBookCommandHandler;

    @Mock
    private UpdateBookCommandHandler updateBookCommandHandler;

    @Mock
    private DeleteBookCommandHandler deleteBookCommandHandler;

    @Mock
    private GetAllBooksQueryHandler getAllBooksQueryHandler;

    @Mock
    private GetBookByIdQueryHandler getBookByIdQueryHandler;

    @InjectMocks
    private BookApplicationService service;

    @Test
    void shouldCreateBook() {
        // Given
        CreateBookCommand command = new CreateBookCommand("Title", "Author", "ISBN");
        Book expectedBook = new Book();
        when(createBookCommandHandler.handle(command)).thenReturn(expectedBook);

        // When
        Book result = service.createBook(command);

        // Then
        assertEquals(expectedBook, result);
        verify(createBookCommandHandler).handle(command);
    }

    @Test
    void shouldUpdateBook() {
        // Given
        UpdateBookCommand command = new UpdateBookCommand(1L, "Title", "Author", "ISBN");
        Book expectedBook = new Book();
        when(updateBookCommandHandler.handle(command)).thenReturn(expectedBook);

        // When
        Book result = service.updateBook(command);

        // Then
        assertEquals(expectedBook, result);
        verify(updateBookCommandHandler).handle(command);
    }

    @Test
    void shouldDeleteBook() {
        // Given
        DeleteBookCommand command = new DeleteBookCommand(1L);

        // When
        service.deleteBook(command);

        // Then
        verify(deleteBookCommandHandler).handle(command);
    }

    @Test
    void shouldGetAllBooks() {
        // Given
        List<Book> expectedBooks = Arrays.asList(new Book(), new Book());
        when(getAllBooksQueryHandler.handle(any(GetAllBooksQuery.class))).thenReturn(expectedBooks);

        // When
        List<Book> result = service.getAllBooks();

        // Then
        assertEquals(expectedBooks, result);
        verify(getAllBooksQueryHandler).handle(any(GetAllBooksQuery.class));
    }

    @Test
    void shouldGetBookById() {
        // Given
        Long bookId = 1L;
        Book expectedBook = new Book();
        when(getBookByIdQueryHandler.handle(new GetBookByIdQuery(bookId))).thenReturn(Optional.of(expectedBook));

        // When
        Optional<Book> result = service.getBookById(bookId);

        // Then
        assertTrue(result.isPresent());
        assertEquals(expectedBook, result.get());
        verify(getBookByIdQueryHandler).handle(new GetBookByIdQuery(bookId));
    }
}