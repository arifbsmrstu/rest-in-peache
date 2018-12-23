package com.example.rokomarifinal.security;

public class SecurityConstant {
    public static final String SECRET = "secre";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final Long EXPIRATION_TIME  = 864_000_000L; // 1 Day session for a token
}
