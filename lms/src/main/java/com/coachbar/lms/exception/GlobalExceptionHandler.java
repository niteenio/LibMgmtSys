package com.coachbar.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.coachbar.lms.entity.ResponseStructure;

@ControllerAdvice
public class GlobalExceptionHandler {

	// Handle BookNotFoundException
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleBookNotFoundException(BookNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setData("Book not found");
		return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	}

	// Handle Generic Exceptions (for unexpected errors)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseStructure<String>> handleGenericException(Exception exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("An unexpected error occurred: " + exception.getMessage());
		structure.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		structure.setData("Something went wrong");
		return new ResponseEntity<>(structure, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
