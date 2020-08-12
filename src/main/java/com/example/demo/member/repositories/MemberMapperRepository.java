package com.example.demo.member.repositories;

import com.example.demo.member.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapperRepository extends MemberRepository {
    void update(Member member);
    void delete(Long id);
}
