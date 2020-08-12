package com.example.demo.controller;

import com.example.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
