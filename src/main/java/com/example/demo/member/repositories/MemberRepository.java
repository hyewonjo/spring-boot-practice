package com.example.demo.member.repositories;

import com.example.demo.member.Member;
import com.example.demo.member.dto.MemberDTO;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Long save(Member member);
    Member findById(Long id);
    Member findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);
    List<MemberDTO.Response> findAll();
}