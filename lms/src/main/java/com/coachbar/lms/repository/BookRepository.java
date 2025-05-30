package com.coachbar.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coachbar.lms.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}