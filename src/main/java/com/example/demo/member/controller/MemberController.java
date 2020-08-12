package com.example.demo.member.controller;

import com.example.demo.member.service.MemberService;
import com.example.demo.member.dto.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.example.demo.common.RedirectViewUtil.backWithMessage;
import static com.example.demo.common.RedirectViewUtil.redirectWithMessage;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/createView")
    public String createView(ModelMap modelMap,
                             @ModelAttribute("member") MemberDTO.Request request) {
        return "member/createView";
    }

    @PostMapping(value = {"", "/"})
    public String createUser(ModelMap modelMap,
                             @ModelAttribute("member") @Valid MemberDTO.Request request,
                             Errors errors) {
        // validation result
        if (errors.hasErrors()) {
            modelMap.addAttribute("errors", errors);
            return createView(modelMap, request);
        }

        try {
            memberService.createMember(request);
            return redirectWithMessage(modelMap, "/", "회원가입이 완료되었습니다.");
        } catch (RuntimeException ex) {
            // todo back -1 하면 post /member로 가서 문제가 있음.
            return backWithMessage(modelMap, ex.getMessage());
        }
    }
}
