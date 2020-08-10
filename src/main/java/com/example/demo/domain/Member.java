package com.example.demo.domain;

import com.example.demo.member.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginId;
    private String password;
    private String name;

    private int active;
    private String roles = "";
    private String permissions = "";

    protected Member() {
    }

//    public Member(String loginId, String password, String roles, String permissions ){
//        this.loginId = loginId;
//        this.password = password;
//        this.roles = roles;
//        this.permissions = permissions;
//
//        this.active = 1;
//    }

    public Member(MemberDTO.Request request, PasswordEncoder passwordEncoder) {
        this.loginId = request.getLoginId();
        this.password = passwordEncoder.encode(request.getPassword());
        this.name = request.getName();
        // this.roleType = request.getRoleType();
    }

    public List<String> getRoleList() {
        if (roles != null && roles.length() > 0) {
            return Arrays.asList(roles.split(","));
        }

        return new ArrayList<>();
    }

    public List<String> getPermissionsList() {
        if (permissions != null && permissions.length() > 0) {
            return Arrays.asList(permissions.split(","));
        }

        return new ArrayList<>();
    }
}
