package com.coachbar.lms.service;

import com.coachbar.lms.dao.BookDao;
import com.coachbar.lms.entity.Book;
import com.coachbar.lms.entity.ResponseStructure;
import com.coachbar.lms.exception.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookDao bookDao;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        Book book = new Book(1, "Java", "Author", LocalDate.now());
        when(bookDao.addBook(any(Book.class))).thenReturn(book);

        ResponseEntity<ResponseStructure<Book>> response = bookService.addBook(book);

        assertEquals("Book Added Sucessfully", response.getBody().getMessage());
        assertEquals(book, response.getBody().getData());
        verify(bookDao, times(1)).addBook(book);
    }

    @Test
    void testGetAllBooks() {
        Book book1 = new Book(1, "Java", "Author1", LocalDate.now());
        Book book2 = new Book(2, "Spring", "Author2", LocalDate.now());

        when(bookDao.getAllBook()).thenReturn(Arrays.asList(book1, book2));

        ResponseEntity<ResponseStructure<List<Book>>> response = bookService.getAllBook();

        assertEquals(2, response.getBody().getData().size());
        assertEquals(" Books Found ", response.getBody().getMessage());
        verify(bookDao, times(1)).getAllBook();
    }

    @Test
    void testGetBookById_Found() {
        Book book = new Book(1, "Java", "Author", LocalDate.now());
        when(bookDao.getBookById(1)).thenReturn(Optional.of(book));

        ResponseEntity<ResponseStructure<Book>> response = bookService.getBookById(1);

        assertTrue(response.getBody().getData().isPresent());
        assertEquals("Books Found By your Id ", response.getBody().getMessage());
        verify(bookDao, times(1)).getBookById(1);
    }

    @Test
    void testGetBookById_NotFound() {
        when(bookDao.getBookById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(BookNotFoundException.class, () -> bookService.getBookById(1));
        assertTrue(exception.getMessage().contains("Books not found with your id"));
        verify(bookDao, times(1)).getBookById(1);
    }

    @Test
    void testUpdateBookById_Success() {
        int bookId = 1;
        Book existingBook = new Book(bookId, "Java", "Author", LocalDate.now());
        Book updatedBook = new Book(bookId, "Advanced Java", "New Author", LocalDate.now());

        when(bookDao.getBookById(bookId)).thenReturn(Optional.of(existingBook));
        when(bookDao.addBook(any(Book.class))).thenReturn(updatedBook);

        ResponseEntity<ResponseStructure<Book>> response = bookService.updateBookById(updatedBook, bookId);

        assertEquals("Book updated successfully with ID: " + bookId, response.getBody().getMessage());
        assertEquals(updatedBook, response.getBody().getData());
        verify(bookDao, times(1)).getBookById(bookId);
        verify(bookDao, times(1)).addBook(updatedBook);
    }

    @Test
    void testUpdateBookById_NotFound() {
        int bookId = 1;
        Book updatedBook = new Book(bookId, "Advanced Java", "New Author", LocalDate.now());

        when(bookDao.getBookById(bookId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(BookNotFoundException.class, () -> bookService.updateBookById(updatedBook, bookId));
        assertTrue(exception.getMessage().contains("Book not found with ID"));
        verify(bookDao, times(1)).getBookById(bookId);
    }

    @Test
    void testDeleteBookById_Success() {
        int bookId = 1;
        Book book = new Book(bookId, "Java", "Author", LocalDate.now());

        when(bookDao.getBookById(bookId)).thenReturn(Optional.of(book));
        when(bookDao.deleteById(bookId)).thenReturn(true);

        ResponseEntity<ResponseStructure<String>> response = bookService.deleteBookById(bookId);

        assertEquals("Book Dleted By Id : " + bookId, response.getBody().getMessage());
        assertEquals("Deleted successfully", response.getBody().getData());
        verify(bookDao, times(1)).getBookById(bookId);
        verify(bookDao, times(1)).deleteById(bookId);
    }

    @Test
    void testDeleteBookById_NotFound() {
        int bookId = 1;
        when(bookDao.getBookById(bookId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(BookNotFoundException.class, () -> bookService.deleteBookById(bookId));
        assertTrue(exception.getMessage().contains("Books not found with your id"));
        verify(bookDao, times(1)).getBookById(bookId);
    }
}
