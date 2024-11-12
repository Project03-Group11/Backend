package com.group11.shelftalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.group11.shelftalk.models.*;
import java.util.List;

public interface ClubRepository extends JpaRepository <Club, Integer>{
    List<Club> findByNameContainingIgnoreCase(String name);
    List<Club> findByUserId(int userId);
}