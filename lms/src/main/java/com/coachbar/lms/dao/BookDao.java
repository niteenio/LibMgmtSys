package com.coachbar.lms.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coachbar.lms.entity.Book;
import com.coachbar.lms.repository.BookRepository;

@Repository
public class BookDao {

	@Autowired
	private BookRepository bookRepository;

//	Add Book	
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}

//	Get All Books
	public List<Book> getAllBook() {
		return bookRepository.findAll();
	}

//	Get Book by ID
	public Optional<Book> getBookById(int id) {
		return bookRepository.findById(id);
	}

//	Update Book by ID
	public Book UpdateBookById(Book book) {
		return bookRepository.save(book);
	}

//	Delete Book by ID
	public boolean deleteById(int id) {
		Optional<Book> recBook = getBookById(id);
		if (recBook.isPresent()) {
			bookRepository.delete(recBook.get());
			return true;
		}
		return false;
	}
}