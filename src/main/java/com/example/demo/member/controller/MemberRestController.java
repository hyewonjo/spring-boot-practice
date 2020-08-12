package com.example.demo.member.controller;

import com.example.demo.member.service.MemberService;
import com.example.demo.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    // CREATE
    // 사용자 loginId, name, password를 입력받아 새로운 Member를 생성하고 그 결과를 반환
    @PostMapping
    public MemberDTO.Response put(@RequestBody @Valid MemberDTO.Request request) {
        return memberService.createMember(request);
    }

    // READ
    // 해당 loginId의 사용자를 반환
    @GetMapping("/{loginId}")
    public MemberDTO.Response findOne(@PathVariable String loginId) {
        return MemberDTO.Response.of(memberService.getMemberByLoginId(loginId));
    }

    // UPDATE
    // 해당 id의 사용자 name, status을 갱신한 뒤 결과를 반환.
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MemberDTO.Response update(@PathVariable Long id, @RequestBody MemberDTO.Request request) {
        return MemberDTO.Response.of(memberService.updateMember(id, request.getName(), request.getStatus()));
    }

    // DELETE
    // 해당 ID의 사용자를 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        memberService.deleteMember(id);
    }

    // READ
    // 모든 사용자 리스트를 반환
    @GetMapping
    public List<MemberDTO.Response> list() {
        return memberService.getAllMembers();
    }
}
