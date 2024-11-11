package com.group11.shelftalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.group11.shelftalk.models.*;
import java.util.List;

public interface PostRepository extends JpaRepository <Post, Integer>{
    List<Post> findByClubId(int clubId);
    List<Post> findByUserId(int userId);
    List<Post> findAllByOrderByCreatedAtDesc(); // Most recent first
    List<Post> findAllByOrderByCreatedAtAsc();  // Oldest first
}