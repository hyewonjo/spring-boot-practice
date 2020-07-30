package com.example.demo.config.security;

import com.example.demo.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private final Member member;

    public UserPrincipal(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Extract list of permissions (name)
        member.getPermissionsList().forEach(p -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(p);
            authorities.add(authority);
        });

        member.getRoleList().forEach(p -> {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + p);
            authorities.add(authority);
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getLoginId();
    }

    @Override
    /*
    계정이 만료되지 않았는지를 리턴.
    true 이면 만료되지 않음을 의미
     */
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    /*
    계정이 잠겨있지 않은지를 리턴.
    true 이면 계정이 잠겨있지 않음을 의미
     */
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    /*
    계정의 페스워드가 만료되지 않았는지를 리턴
    true 이면 패스워드가 만료되지 않음을 의미
     */
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    /*
    계정이 사용 가능한 계정인지를 리턴.
    true 이면 사용가능한 계정임을 의미
     */
    public boolean isEnabled() {
        return member.getActive() == 1;
    }
}
