package com.group11.shelftalk.service;

import java.util.List;

public class OpenLibraryBook {
    private String title;
    private List<String> isbn;
    private Integer number_of_pages_median;
    private List<String> author_name;
    private List<String> first_sentence;
    private String cover_edition_key;
    private Integer cover_i;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getIsbn() {
        return isbn;
    }

    public void setIsbn(List<String> isbn) {
        this.isbn = isbn;
    }

    public Integer getNumber_of_pages_median() {
        return number_of_pages_median;
    }

    public void setNumber_of_pages_median(Integer number_of_pages_median) {
        this.number_of_pages_median = number_of_pages_median;
    }

    public List<String> getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(List<String> author_name) {
        this.author_name = author_name;
    }

    public List<String> getFirst_sentence() {
        return first_sentence;
    }

    public void setFirst_sentence(List<String> first_sentence) {
        this.first_sentence = first_sentence;
    }

    public String getCover_edition_key() {
        return cover_edition_key;
    }

    public void setCover_edition_key(String cover_edition_key) {
        this.cover_edition_key = cover_edition_key;
    }

    public Integer getCover_i() {
        return cover_i;
    }

    public void setCover_i(Integer cover_i) {
        this.cover_i = cover_i;
    }
}
