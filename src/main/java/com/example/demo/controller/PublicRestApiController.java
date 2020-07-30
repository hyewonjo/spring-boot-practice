package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/public")
@AllArgsConstructor
public class PublicRestApiController {
    private final MemberService memberService;

    @GetMapping("test1")
    public String test1() {
        return "API Test 1";
    }

    @GetMapping("test2")
    public String test2() {
        return "API Test 2";
    }

    @GetMapping("users")
    public List<Member> allUsers() {
        return memberService.findMembers();
    }
}
