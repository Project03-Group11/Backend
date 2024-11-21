package com.group11.shelftalk.models;
import jakarta.persistence.*;

@Entity
@Table(name = "Club")
public class Club{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId; //owner of club
    private int bookId; //id of current book read
    private String name; //club name
    private String description; //club description


    public Club() {
    }

    public Club (int userId, int bookId, String name, String description) {
        this.userId = userId; //owner of club's id
        this.bookId = bookId; //current book read's book id
        this.name = name; //club name
        this.description = description; // club description
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

}