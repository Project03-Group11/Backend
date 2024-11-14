package com.group11.shelftalk.controller;

import com.group11.shelftalk.models.Member;
import com.group11.shelftalk.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/member")
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @CrossOrigin
    @PostMapping("/add")
    public Member addMember(@RequestBody Map<String,String> body) {
        int userId = Integer.parseInt(body.get("userId"));
        int clubId = Integer.parseInt(body.get("clubId"));

        Member newMember = new Member(userId,clubId);
        memberRepository.save(newMember);
        return newMember;
    }

    @CrossOrigin
    @GetMapping("/get-all")
    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/get/user/{userId}")
    public List<Member> getByUser(@PathVariable Integer userId) {
        return memberRepository.findByUserId(userId);
    }

    @CrossOrigin
    @GetMapping("/get/club/{clubId}")
    public List<Member> getByClub(@PathVariable Integer clubId) {
        return memberRepository.findByClubId(clubId);
    }


    @CrossOrigin
    @DeleteMapping("/remove/{id}")
    public boolean deleteMember(@PathVariable("id") Integer id) {
        if(memberRepository.existsById(id)){
            memberRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @CrossOrigin
    @PutMapping("/update/{id}")
    public Member updateMember(@PathVariable("id") Integer id, @RequestBody Map<String, String> body) {
        Member currMember = memberRepository.findById(id).orElse(null);
        if(currMember != null){
            if(body.containsKey("userId")){
                currMember.setUserId(Integer.parseInt(body.get("userId")));
            }
            if(body.containsKey("clubId")){
                currMember.setClubId(Integer.parseInt(body.get("clubId")));
            }
            memberRepository.save(currMember);
        }
        return currMember;
    }

}