package com.group11.shelftalk.models;
import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String username;
    private String profile_pic;

    public User() {
    }

    public User (String email) {
        this.email = email;
    }

    public User (int id, String email, String username, String profile_pic) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.profile_pic = profile_pic;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePic() {
        return profile_pic;
    }

    public void setProfilePic(String profile_pic) {
        this.profile_pic = profile_pic;
    }
}