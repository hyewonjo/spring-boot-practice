package com.example.demo.member.dto;

import com.example.demo.domain.Member;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MemberDTO {

    @Data
    public static class Request {
        @NotNull @NotEmpty
        private String loginId;
        private String password;
        private String name;
    }

    @Data
    public static class Response {
        private String loginId;
        private String name;
        // private RoleType roleType;

        public static Response of(Member member) {
            Response response = new Response();

            response.setLoginId(member.getLoginId());
            response.setName(member.getName());

            return response;
        }
    }
}
