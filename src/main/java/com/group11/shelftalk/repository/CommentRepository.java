package com.group11.shelftalk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group11.shelftalk.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
        List<Comment> findByUserId(int userId);
        List<Comment> findByPostId(int postId);
        List<Comment> findByPostIdOrderByCreatedAtDesc(int postId); 
        List<Comment> findByPostIdOrderByCreatedAtAsc(int postId);
}
