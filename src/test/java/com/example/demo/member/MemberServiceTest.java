package com.example.demo.member;

import com.example.demo.member.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    void createUser() {
        MemberDTO.Request request = new MemberDTO.Request();
        request.setLoginId("hyewonj");
        request.setName("조혜원");
        request.setPassword("qwer1234");
        assertThrows(RuntimeException.class, () -> memberService.createUser(request));
    }
}