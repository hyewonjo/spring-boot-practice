package com.example.demo.member.dto;

import com.example.demo.member.Member;
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
        private Integer status;
    }

    @Data
    public static class Response {
        private Long id;
        private String loginId;
        private String name;
        private Integer status;
        // private RoleType roleType;

        public static Response of(Member member) {
            Response response = new Response();

            response.setId(member.getId());
            response.setLoginId(member.getLoginId());
            response.setName(member.getName());
            response.setStatus(member.getStatus());

            return response;
        }
    }
}
