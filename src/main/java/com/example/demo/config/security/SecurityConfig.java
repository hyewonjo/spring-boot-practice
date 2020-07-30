package com.example.demo.config.security;

import com.example.demo.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity(debug = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final MemberService memberService;

    public SecurityConfig(MemberService memberService) {
        this.memberService = memberService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 페이지 권한 설정
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/profile/**").authenticated()
                .antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
                .antMatchers("/api/public/test2").hasAuthority("ACCESS_TEST2")
                .antMatchers("/api/public/users").hasRole("ADMIN")
                .antMatchers("/**").permitAll()
            .and() // 로그인 설정
                .formLogin()
                .loginPage("/login")
                .permitAll()
            .and()
                .exceptionHandling().accessDeniedPage("/access/denied")
            .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
            .and()
                .rememberMe()
                .tokenValiditySeconds(2592000).key("mySecretKey");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

}
