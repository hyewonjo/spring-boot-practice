package com.example.demo.member;

import com.example.demo.domain.Member;
import com.example.demo.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    // private final PasswordEncoder passwordEncoder;

    // 회원가입
    public MemberDTO.Response createUser(MemberDTO.Request request) {
        // 아이디 중복체크
        if (memberRepository.existsByLoginId(request.getLoginId())) {
            throw new RuntimeException("이미 존재하는 계정입니다.");
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // new Member, save
        Member member = new Member(request, passwordEncoder);
        Member save = memberRepository.save(member);
        return MemberDTO.Response.of(save);
    }
}
