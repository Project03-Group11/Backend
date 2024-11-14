package com.group11.shelftalk.service;

import com.group11.shelftalk.models.Book;
import com.group11.shelftalk.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    private static final String[] GENRES = {
            "Drama", "Fable", "Fairy Tale", "Fantasy", "Fiction", "Folklore", "Historical Fiction",
            "Horror", "Humor", "Legend", "Mystery", "Mythology", "Poetry", "Science Fiction", "Tall Tale"
    };

    private static final int TOTAL_BOOKS = 1500;
    private static final int BOOKS_PER_GENRE = TOTAL_BOOKS / GENRES.length;

    public void fetchAndSaveBooks() {
        Set<String> fetchedIsbns = new HashSet<>();

        for (String genre : GENRES) {
            System.out.println("Starting to fetch books for genre: " + genre);
            fetchBooksByGenre(genre, BOOKS_PER_GENRE, fetchedIsbns);
            System.out.println("Finished fetching books for genre: " + genre);
        }
    }

    private void fetchBooksByGenre(String genre, int limit, Set<String> fetchedIsbns) {
        int fetchedCount = 0;
        int page = 1;

        while (fetchedCount < limit) {
            String apiUrl = "https://openlibrary.org/search.json?q=subject:" + genre.toLowerCase() + "&page=" + page;
            ResponseEntity<OpenLibraryResponse> response = restTemplate.getForEntity(apiUrl, OpenLibraryResponse.class);
            OpenLibraryResponse openLibraryResponse = response.getBody();

            if (openLibraryResponse != null && openLibraryResponse.getDocs() != null) {
                for (OpenLibraryBook openLibraryBook : openLibraryResponse.getDocs()) {
                    if (fetchedCount >= limit) {
                        break;
                    }

                    String isbn = openLibraryBook.getIsbn() != null && !openLibraryBook.getIsbn().isEmpty() ? openLibraryBook.getIsbn().get(0) : null;
                    if (isbn != null && !fetchedIsbns.contains(isbn)) {
                        fetchedIsbns.add(isbn);
                        String coverImgUrl = null;
                        if (openLibraryBook.getCover_i() != null) {
                            coverImgUrl = "https://covers.openlibrary.org/b/id/" + openLibraryBook.getCover_i() + "-L.jpg";
                        } else if (openLibraryBook.getCover_edition_key() != null) {
                            coverImgUrl = "https://covers.openlibrary.org/b/olid/" + openLibraryBook.getCover_edition_key() + "-L.jpg";
                        }

                        // Truncate summary to 200 characters and add "..." if it's too long
                        String summary = openLibraryBook.getFirst_sentence() != null && !openLibraryBook.getFirst_sentence().isEmpty() ? openLibraryBook.getFirst_sentence().get(0) : "";
                        if (summary.length() > 200) {
                            summary = summary.substring(0, 200) + "...";
                        }

                        // Create book object and save to DB
                        Book book = new Book(
                                openLibraryBook.getTitle(),
                                isbn,
                                openLibraryBook.getNumber_of_pages_median() != null ? openLibraryBook.getNumber_of_pages_median() : 0,
                                openLibraryBook.getAuthor_name() != null && !openLibraryBook.getAuthor_name().isEmpty() ? openLibraryBook.getAuthor_name().get(0) : null,
                                genre,
                                summary,
                                coverImgUrl
                        );

                        bookRepository.save(book);
                        fetchedCount++;

                        System.out.println("Added book: " + openLibraryBook.getTitle() + " (ISBN: " + isbn + ")");
                    }
                }
            }
            page++;

            System.out.println("Fetched " + fetchedCount + " books for genre: " + genre + " (Page " + page + ")");
        }

        System.out.println("Completed fetching books for genre: " + genre + ". Total books added: " + fetchedCount);
    }
}
