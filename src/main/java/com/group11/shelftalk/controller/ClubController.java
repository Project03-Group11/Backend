package com.group11.shelftalk.controller;

import com.group11.shelftalk.models.Club;
import com.group11.shelftalk.repository.ClubRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/club")
public class ClubController {
    @Autowired
    private ClubRepository clubRepository;

    @CrossOrigin
    @PostMapping("/add")
    public Club addClub(@RequestBody Map<String,String> body) {
        int userId = Integer.parseInt(body.get("userId"));
        int bookId = Integer.parseInt(body.get("bookId"));
        String name = body.get("name");
        String description = body.get("description");

        Club newClub = new Club(userId,bookId, name, description);
        clubRepository.save(newClub);
        return newClub;
    }

    @CrossOrigin
    @GetMapping("/get-all")
    public List<Club> getAll() {
        return clubRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/get/{id}")
    public Club getById(@PathVariable Integer id) {
        return clubRepository.findById(id).orElse(null);
    }

    @CrossOrigin
    @GetMapping("/get/user/{userId}")
    public List<Club> getByUser(@PathVariable Integer userId) {
        return clubRepository.findByUserId(userId);
    }

    @CrossOrigin
    @GetMapping("/get/name/{name}")
    public List<Club> getByClub(@PathVariable String name) {
        return clubRepository.findByNameContainingIgnoreCase(name);
    }

    @CrossOrigin
    @DeleteMapping("/remove/{id}")
    public boolean deleteClub(@PathVariable("id") Integer id) {
        if(clubRepository.existsById(id)){
            clubRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @CrossOrigin
    @PutMapping("/update/{id}")
    public Club updateClub(@PathVariable("id") Integer id, @RequestBody Map<String, String> body) {
        Club currClub = clubRepository.findById(id).orElse(null);
        if(currClub != null){
            if(body.containsKey("bookId")){
                currClub.setBookId(Integer.parseInt(body.get("bookId")));
            }
            if(body.containsKey("name")){
                currClub.setName(body.get("name"));
            }
            if(body.containsKey("description")){
                currClub.setDescription(body.get("description"));
            }
            clubRepository.save(currClub);
        }
        return currClub;
    }

}