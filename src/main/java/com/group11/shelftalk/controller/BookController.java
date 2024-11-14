package com.group11.shelftalk.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group11.shelftalk.models.Book;
import com.group11.shelftalk.repository.BookRepository;
import com.group11.shelftalk.service.BookService;

@RestController
@RequestMapping("api/book")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    // This will be used to control the service. Should only be Ran once so afterwards it will be false
    private boolean allowService = false;

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
    @GetMapping("/get-all")
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

    @RequestMapping("/fetch-external-books")
    public ResponseEntity<String> fetchExternalBooks() {
        if (!allowService) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Service has already been run.");
        }
        try {
            bookService.fetchAndSaveBooks();
            allowService = false;  // Never allow after first use
            return ResponseEntity.ok("Books fetched and saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch and save books: " + e.getMessage());
        }
    }
}
