package com.group11.shelftalk.service;

import java.util.List;

public class OpenLibraryResponse {
    private List<OpenLibraryBook> docs; 

    public List<OpenLibraryBook> getDocs() {
        return docs;
    }

    public void setDocs(List<OpenLibraryBook> docs) {
        this.docs = docs;
    }
}
