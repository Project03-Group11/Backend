package com.group11.shelftalk.models;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "Post")
public class Post{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId; //owner of club
    private int clubId; //the club it is referring
    private String discussion; //club name
    private int likes;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Post() {
    }

    public Post (int userId, int clubId, String discussion, int likes) {
        this.userId = userId;
        this.clubId = clubId;
        this.discussion = discussion;
        this.likes = likes;
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

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getDiscussion() {
        return discussion;
    }

    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes){
        this.likes = likes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}