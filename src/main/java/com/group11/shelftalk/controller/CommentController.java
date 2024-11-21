package com.group11.shelftalk.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.group11.shelftalk.models.Comment;
import com.group11.shelftalk.repository.CommentRepository;

@RestController
@RequestMapping("api/comment")
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @CrossOrigin
    @PostMapping("/add")
    public Comment addPost(@RequestBody Map<String, String> body){
        int postId = Integer.parseInt(body.get("postId"));
        int userId = Integer.parseInt(body.get("userId"));
        String comment = body.get("comment");

        Comment newComment = new Comment(postId, userId, comment);
        commentRepository.save(newComment);
        return newComment;
    }

    @CrossOrigin
    @GetMapping("/get-all")
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/get/{id}")
    public Comment getById(@PathVariable Integer id){
        return commentRepository.findById(id).orElse(null);
    }

    @CrossOrigin
    @GetMapping("/get/user/{userId}")
    public List<Comment> getByUser(@PathVariable Integer userId){
        return commentRepository.findByUserId(userId);
    }

    @CrossOrigin
    @GetMapping("/get/post/{postId}")
    public List<Comment> getByPost(@PathVariable Integer postId){
        return commentRepository.findByPostId(postId);
    }

    @CrossOrigin
    @GetMapping("/get/post/{postId}/recent")
    public List<Comment> getMostRecent(@PathVariable Integer postId) {
        return commentRepository.findByPostIdOrderByCreatedAtDesc(postId);
    }

    @CrossOrigin
    @GetMapping("/get/post/{postId}/oldest")
    public List<Comment> getOldest(@PathVariable Integer postId) {
        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId);
    }

    @CrossOrigin
    @DeleteMapping("/remove/{id}")
    public boolean deletePost(@PathVariable("id") Integer id) {
        if(commentRepository.existsById(id)){
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @CrossOrigin
    @PutMapping("/update/{id}")
    public Comment updatePost(@PathVariable("id") Integer id, @RequestBody Map<String, String> body) {
        Comment currCom = commentRepository.findById(id).orElse(null);
        if(currCom != null){
            if(body.containsKey("comment")){
                currCom.setComment(body.get("comment"));
            }
            commentRepository.save(currCom);
        }
        return currCom;
    }

}
