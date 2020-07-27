package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        Member member1 = new Member();
        member1.setLoginId("hyewon");
        member1.setPassword("asdf1234");

        // when
        Long saveId = memberService.join(member1);

        // then
        Member findMember = memberService.findOne(saveId).get();

        assertThat(findMember.getLoginId()).isEqualTo(member1.getLoginId());
    }

    @Test
    void 회원가입_중복테스트() {
        // given
        Member member1 = new Member();
        member1.setLoginId("hyewon");
        member1.setPassword("asdf1234");

        Member member2 = new Member();
        member2.setLoginId("hyewon");
        member2.setPassword("asdf1234");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}