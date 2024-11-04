package com.group11.shelftalk.controller;

import com.group11.shelftalk.models.User;
import com.group11.shelftalk.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @PostMapping("/add")
    public User addUser(@RequestBody Map<String,String> body) {
        String email = body.get("email");
        User existingUser = userRepository.findByEmail(email);
        if(existingUser != null){
            return null;
        }
        User newUser = new User(email);
        userRepository.save(newUser);
        return newUser;
    }

    @CrossOrigin
    @GetMapping("/get-all")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/get/{email}")
    public User getByEmail(@PathVariable String email) {
        User response = userRepository.findByEmail(email);
        if(response == null){
            return null;
        }
        return response;
    }

    @CrossOrigin
    @DeleteMapping("/remove/{id}")
    public boolean deleteUser(@PathVariable("id") Integer id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @CrossOrigin
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable("id") Integer id, @RequestBody Map<String, String> body) {
        User currUser = userRepository.findById(id).get();
        if(currUser != null){
            if(body.containsKey("username")){
                currUser.setUsername(body.get("username"));
            }
            if(body.containsKey("profilePic")){
                currUser.setProfilePic(body.get("profilePic"));
            }

            userRepository.save(currUser);
            return currUser;
        }
        return null;
    }
}