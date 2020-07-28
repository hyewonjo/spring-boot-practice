package com.example.demo.domain.dto;

import com.example.demo.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MemberDTO {

    private String loginId;
    private String password;

    public Member toEntity() {
        Member member = new Member();
        member.setLoginId(loginId);
        member.setPassword(password);

        return member;
    }
}
