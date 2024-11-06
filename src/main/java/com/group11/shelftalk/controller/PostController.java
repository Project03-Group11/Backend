package com.group11.shelftalk.controller;

import com.group11.shelftalk.models.Post;
import com.group11.shelftalk.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/post")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @CrossOrigin
    @PostMapping("/add")
    public Post addPost(@RequestBody Map<String,String> body) {
        int userId = Integer.parseInt(body.get("userId"));
        int clubId = Integer.parseInt(body.get("clubId"));
        String discussion = body.get("discussion");
        int likes = 0;
        if(body.containsKey("likes")){
            likes = Integer.parseInt(body.get("likes"));
        }
    

        Post newPost = new Post(userId,clubId, discussion, likes);
        postRepository.save(newPost);
        return newPost;
    }

    @CrossOrigin
    @GetMapping("/get-all")
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/get/{id}")
    public Post getById(@PathVariable Integer id) {
        return postRepository.findById(id).orElse(null);
    }

    @CrossOrigin
    @GetMapping("/get/user/{userId}")
    public List<Post> getByUser(@PathVariable Integer userId) {
        return postRepository.findByUserId(userId);
    }

    @CrossOrigin
    @GetMapping("/get/club/{clubId}")
    public List<Post> getByClub(@PathVariable Integer clubId) {
        return postRepository.findByClubId(clubId);
    }

    @CrossOrigin
    @GetMapping("/get/recent")
    public List<Post> getMostRecent() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @CrossOrigin
    @GetMapping("/get/oldest")
    public List<Post> getOldest() {
        return postRepository.findAllByOrderByCreatedAtAsc();
    }

    @CrossOrigin
    @DeleteMapping("/remove/{id}")
    public boolean deletePost(@PathVariable("id") Integer id) {
        if(postRepository.existsById(id)){
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @CrossOrigin
    @PutMapping("/update/{id}")
    public Post updatePost(@PathVariable("id") Integer id, @RequestBody Map<String, String> body) {
        Post currPost = postRepository.findById(id).orElse(null);
        if(currPost != null){
            if(body.containsKey("discussion")){
                currPost.setDiscussion(body.get("discussion"));
            }
            postRepository.save(currPost);
        }
        return currPost;
    }

}