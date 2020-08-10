package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    // todo  @RequestMapping(value = {"", "/"}) 왜 두개 다 선언해야하죠
    // todo @PreAuthorize("isAuthenticated()")
    // todo @MenuAdvice(menuId = 1L)
    public String index(ModelMap modelMap) {
        return "index";
    }








//    @GetMapping("login")
//    public String login() {
//        return "login";
//    }
//
//    // 로그인 성공 페이지
//    @GetMapping("/login/result")
//    public String loginResult() {
//        return "login-success";
//    }
//
//    // 로그아웃 결과 페이지
//    @GetMapping("/user/logout/result")
//    public String dispLogout() {
//        return "/logout";
//    }
}
