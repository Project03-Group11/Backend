package com.group11.shelftalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.group11.shelftalk.models.*;

public interface UserRepository extends JpaRepository <User, Integer>{
    User findByEmail(String email);
}