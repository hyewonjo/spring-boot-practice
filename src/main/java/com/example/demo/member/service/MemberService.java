package com.example.demo.member.service;

import com.example.demo.member.Member;
import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.repositories.MemberMapperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapperRepository memberRepository;
    // private final PasswordEncoder passwordEncoder;

    // CREATE
    public MemberDTO.Response createMember(MemberDTO.Request request) {
        // 아이디 중복체크
        if (memberRepository.existsByLoginId(request.getLoginId())) {
            throw new RuntimeException("이미 존재하는 계정입니다.");
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // new Member, save
        Member member = new Member(request, passwordEncoder);
        memberRepository.save(member);

        return MemberDTO.Response.of(member);
    }

    // READ
    // 해당 loginId의 사용자를 반환
    public Member getMemberByLoginId(String loginId) {

        return memberRepository.findByLoginId(loginId);
    }

    // UPDATE
    // 해당 loginId의 name, status 갱신한 뒤 결과를 반환.
    public Member updateMember(Long id, String name, Integer status) {
        Member member = memberRepository.findById(id);
        if (name != null) {
            member.setName(name);
        }
        if (status != null) {
            member.setStatus(status);
        }
        memberRepository.update(member);

        return member;
    }

    // READ
    // 모든 사용자 리스트를 반환
    public List<MemberDTO.Response> getAllMembers() {
        // todo List<Member> 말고 List<MemberDTO.Response> 하고싶.
        return memberRepository.findAll();
    }

    // DELETE
    // 해당 ID의 사용자를 삭제
    public void deleteMember(Long id) {
        memberRepository.delete(id);
    }
}
