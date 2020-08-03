package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/public")
@RequiredArgsConstructor
// @CrossOrigin
public class PublicRestApiController {
    private MemberService memberService;

    // Available to all authenticated users
    @GetMapping("test")
    public String test1() {
        return "API Test 1";
    }

    // Available to managers
    @GetMapping("management/reports")
    public String test2() {
        return "API Test 2";
    }

    // Available to admin
    @GetMapping("admin/users")
    public List<Member> allUsers() {
        return memberService.findMembers();
    }
}
