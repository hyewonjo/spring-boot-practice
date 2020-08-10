package com.example.demo.member;

import com.example.demo.domain.Member;
import com.example.demo.member.MemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByLoginId(String loginId);

    @Override
    boolean existsByLoginId(String loginId);
}
