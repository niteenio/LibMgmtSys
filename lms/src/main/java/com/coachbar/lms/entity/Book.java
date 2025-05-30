package com.coachbar.lms.entity;

import java.time.LocalDate;
import java.util.function.BooleanSupplier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Title is required")
	@Column(nullable = false)
	private String title;

	@NotBlank(message = "Author is required")
	@Column(nullable = false)
	private String author;

	@NotNull(message = "Publication year is required")
	@Column(nullable = false)
	private LocalDate publicationYear;

	// Constructors
	public Book() {
	}

	public Book(long id, String title, String author, LocalDate publicationYear) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
	}

	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDate getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(LocalDate publicationYear) {
		this.publicationYear = publicationYear;
	}

	public BooleanSupplier isPresent() {
		// TODO Auto-generated method stub
		return null;
	}
}
