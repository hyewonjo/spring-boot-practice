package com.example.demo.controller;

import com.example.demo.domain.dto.MemberDTO;
import com.example.demo.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 가입 폼 페이지
    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    // 회원 가입 처리
    @PostMapping("/signup")
    public String signup(@RequestParam(name = "loginId") String loginId, @RequestParam(name = "password") String password) {
        MemberDTO memberDTO = new MemberDTO(loginId, password);
        memberService.join(memberDTO);

        return "redirect:/signup";
    }

    // 내 정보 페이지
    @GetMapping("/user/info")
    public String dispMyInfo() {
        return "/myinfo";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        return "/admin";
    }

    // 접근 거부 페이지
    @GetMapping("/access/denied")
    public String dispDenied() {
        return "/denied";
    }
}
