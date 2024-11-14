package com.group11.shelftalk.models;
import jakarta.persistence.*;

@Entity
@Table(name = "Member")
public class Member{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;
    private int clubId;

    public Member() {
    }

    public Member (int userId, int clubId) {
        this.userId = userId;
        this.clubId = clubId;
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
}