package com.example.demo.member;

import com.example.demo.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Member findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);
    List<Member> findAll();
}
