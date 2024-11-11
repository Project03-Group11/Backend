package com.group11.shelftalk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group11.shelftalk.models.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByGenre(String genre);
}
