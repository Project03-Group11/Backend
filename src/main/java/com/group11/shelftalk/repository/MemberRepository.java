package com.group11.shelftalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.group11.shelftalk.models.*;
import java.util.List;

public interface MemberRepository extends JpaRepository <Member, Integer>{
    List<Member> findByUserId(int userId);
    List<Member> findByClubId(int clubId);
}