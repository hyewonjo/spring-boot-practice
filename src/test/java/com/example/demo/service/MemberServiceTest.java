package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.dto.MemberDTO;
import com.example.demo.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    void join() {
        // given
        MemberDTO memberDTO1 = new MemberDTO("hyewon", "asdf1234");
        Member member1 = memberDTO1.toEntity();
        // when
        Long saveId = memberService.join(memberDTO1);

        // then
        Member findMember = memberService.findOne(saveId).get();

        assertThat(findMember.getLoginId()).isEqualTo(member1.getLoginId());
    }

    @Test
    void 회원가입_중복테스트() {
        // given
        MemberDTO memberDTO1 = new MemberDTO("hyewon", "asdf1234");
        MemberDTO memberDTO2 = new MemberDTO("hyewon", "asdf1234");

        // when
        memberService.join(memberDTO1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(memberDTO2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 ID입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}