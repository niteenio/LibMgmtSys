package com.coachbar.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coachbar.lms.entity.Book;
import com.coachbar.lms.entity.ResponseStructure;
import com.coachbar.lms.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	// Add Book
	@PostMapping(value = "/saveBooks")
	public ResponseEntity<ResponseStructure<Book>> addBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}

	// Get All Books
	@GetMapping(value = "/getAllBooks")
	public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks() {
		return bookService.getAllBook();
	}

	// Get Book by ID
	@GetMapping("/book/{id}")
	public ResponseEntity<ResponseStructure<Book>> getBookById(@PathVariable int id) {
		return bookService.getBookById(id);
	}

	// Update Book by ID
	@PutMapping("book/{id}")
	public ResponseEntity<ResponseStructure<Book>> updateBookById(@RequestBody Book book, @PathVariable int id) {
		return bookService.updateBookById(book, id);
	}

	// Delete Book by ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteBookById(@PathVariable int id) {
		return bookService.deleteBookById(id);
	}
}