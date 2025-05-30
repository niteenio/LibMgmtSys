package com.coachbar.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coachbar.lms.dao.BookDao;
import com.coachbar.lms.entity.Book;
import com.coachbar.lms.entity.ResponseStructure;
import com.coachbar.lms.exception.BookNotFoundException;

@Service
public class BookService {

	@Autowired
	private BookDao bookDao;

	// Add Book
	public ResponseEntity<ResponseStructure<Book>> addBook(Book book) {
		ResponseStructure<Book> structure = new ResponseStructure<>();
		structure.setData(bookDao.addBook(book));
		structure.setMessage("Book Added Successfully");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<>(structure, HttpStatus.CREATED);
	}

	// Get All Books
	public ResponseEntity<ResponseStructure<List<Book>>> getAllBook() {
		List<Book> books = bookDao.getAllBook();
		if (books.isEmpty()) {
			throw new BookNotFoundException("No books found in the library");
		}

		ResponseStructure<List<Book>> structure = new ResponseStructure<>();
		structure.setData(books);
		structure.setMessage("Books Found");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	// Get Book by ID
	public ResponseEntity<ResponseStructure<Book>> getBookById(int id) {
		Optional<Book> optionalBook = bookDao.getBookById(id);
		if (optionalBook.isEmpty()) {
			throw new BookNotFoundException("Book not found with id: " + id);
		}

		ResponseStructure<Book> structure = new ResponseStructure<>();
		structure.setData(optionalBook.get());
		structure.setMessage("Book Found");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	// Update Book by ID
	public ResponseEntity<ResponseStructure<Book>> updateBookById(Book book, int id) {
		Optional<Book> optionalBook = bookDao.getBookById(id);
		if (optionalBook.isEmpty()) {
			throw new BookNotFoundException("Book not found with ID: " + id);
		}

		Book existingBook = optionalBook.get();
		existingBook.setTitle(book.getTitle());
		existingBook.setAuthor(book.getAuthor());
		existingBook.setPublicationYear(book.getPublicationYear());

		Book updatedBook = bookDao.addBook(existingBook);

		ResponseStructure<Book> structure = new ResponseStructure<>();
		structure.setData(updatedBook);
		structure.setMessage("Book updated successfully");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	// Delete Book by ID
	public ResponseEntity<ResponseStructure<String>> deleteBookById(int id) {
		Optional<Book> optionalBook = bookDao.getBookById(id);
		if (optionalBook.isEmpty()) {
			throw new BookNotFoundException("Book not found with id: " + id);
		}

		bookDao.deleteById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData("Deleted successfully");
		structure.setMessage("Book deleted successfully");
		structure.setStatusCode(HttpStatus.NO_CONTENT.value());
		return new ResponseEntity<>(structure, HttpStatus.NO_CONTENT);
	}
}
