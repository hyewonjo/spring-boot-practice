package com.example.demo.member;

import com.example.demo.member.dto.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public String createUser(@ModelAttribute("member") MemberDTO.Request request) {
        // validation 하고
        // save 하고
        return request.getLoginId() + " / " + request.getPassword() + " / " + request.getName();
    }
}
