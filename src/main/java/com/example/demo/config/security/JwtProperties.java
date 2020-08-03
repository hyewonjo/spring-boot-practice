package com.example.demo.config.security;

public class JwtProperties {
    public static final String SECRET = "hyewonj"; // JWT 토큰 만들때 사용할 secret key
    public static final int EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
