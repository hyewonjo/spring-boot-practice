package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.Role;
import com.example.demo.domain.dto.MemberDTO;
import com.example.demo.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final Logger logger = LoggerFactory.getLogger(MemberService.class);

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     *
     * @param memberDTO
     * @return
     */
    public Long join(MemberDTO memberDTO) {
        // loginId 중복 체크
        validateDuplicateMember(memberDTO);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 비밀번호 암호화
        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));

        return memberRepository.save(memberDTO.toEntity()).getId();
    }

    private void validateDuplicateMember(MemberDTO memberDTO) {
        memberRepository.findByLoginId(memberDTO.getLoginId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 ID입니다.");
                });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 조회 by id
     */
    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member member = memberRepository.findByLoginId(loginId).get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        switch (member.getLoginId()) {
            case "admin":
                authorities.addAll(Arrays.asList(
                        new SimpleGrantedAuthority(Role.ADMIN.getValue()),
                        new SimpleGrantedAuthority("ACCESS_TEST1"),
                        new SimpleGrantedAuthority("ACCESS_TEST2")
                        ));
            case "manager":
                authorities.addAll(Arrays.asList(
                        new SimpleGrantedAuthority(Role.MANAGER.getValue()),
                        new SimpleGrantedAuthority("ACCESS_TEST1")
                ));
            default:
                authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(member.getLoginId(), member.getPassword(), authorities);
    }
}
