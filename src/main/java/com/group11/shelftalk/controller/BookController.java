package com.group11.shelftalk.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.group11.shelftalk.models.Book;
import com.group11.shelftalk.repository.BookRepository;

@RestController
@RequestMapping("api/book")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @CrossOrigin
    @PostMapping("/add")
    public Book addBook(@RequestBody Map<String, String> body){
        String title = body.get("title");
        String ISBN = body.get("ISBN");
        int pageCount = Integer.parseInt(body.get("pageCount"));
        String author = body.get("author");
        String genre = body.get("genre");
        String summary = body.get("summary");
        String coverImg = body.get("coverImg");

        Book newBook = new Book(title, ISBN, pageCount, author, genre, summary, coverImg);
        bookRepository.save(newBook);
        return newBook;
    }

    @CrossOrigin
    @RequestMapping("/get-all")
    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    @CrossOrigin
    @RequestMapping("/get/{id}")
    public Book getById(@PathVariable Integer id){
        return bookRepository.findById(id).get();
    }

    @CrossOrigin
    @RequestMapping("/get/title/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title){
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @CrossOrigin
    @RequestMapping("/get/genre/{genre}")
    public List<Book> getBooksByGenre(@PathVariable String genre){
        return bookRepository.findByGenre(genre);
    }

    @CrossOrigin
    @DeleteMapping("/remove/{id}")
    public boolean removeBook(@PathVariable Integer id){
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
