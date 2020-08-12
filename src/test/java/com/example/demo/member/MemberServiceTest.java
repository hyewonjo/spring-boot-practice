package com.example.demo.member;

import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    void createMemberDuplicateTest() {
        MemberDTO.Request request = new MemberDTO.Request();
        request.setLoginId("asdf");
        request.setName("조혜원");
        request.setPassword("qwer1234");
        assertThrows(RuntimeException.class, () -> memberService.createMember(request));
    }

    @Test
    void getMemberByLoginId() {
        Member member = memberService.getMemberByLoginId("asdf");
        assertThat(member.getId()).isEqualTo(1);
    }

    @Test
    void updateMember() {
        Member member = memberService.updateMember(1L, "조혜원", null);
        assertThat(member.getName()).isEqualTo("조혜원");
        assertThat(member.getStatus()).isEqualTo(1);
    }

    @Test
    void getAllMembers() {
        List<MemberDTO.Response> memberList = memberService.getAllMembers();
        assertThat(memberList.size()).isEqualTo(1);
        System.out.println(memberList);
    }

    @Test
    void deleteMember() {
        Member member = memberService.getMemberByLoginId("asdf");
        memberService.deleteMember(member.getId());
        member = memberService.getMemberByLoginId("asdf");

        assertThat(member).isNull();
    }
}