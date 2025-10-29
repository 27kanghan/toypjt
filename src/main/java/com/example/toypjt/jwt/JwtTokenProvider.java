package com.example.toypjt.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final Key key;
    private final long expirationTime;

    public JwtTokenProvider(@Value("${jwt.secret-key}") String secretKey,
                            @Value("${jwt.expiration-time}") long expirationTime) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.expirationTime = expirationTime;
    }

    /**
     * 사용자 ID로 JWT 토큰 생성
     */

    public String createToken(String userId) {
        Claims claims = Jwts.claims().subject(userId).build();
        Date now = new Date();

        Date validity = new Date(now.getTime() + expirationTime);

        // JWT Builder를 사용해서 토큰 생성

        return Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(validity)
                .signWith(key)
                .compact();
    }

    /**
     * 토큰에서 사용자 ID 추출
     */

    public String getUserId(String token) {
        return Jwts.parser()
                .setSigningKey(key) // 1. 검증 키 설정
                .build()
                .parseClaimsJws(token) // 2. 토큰 파싱 및 서명 검증
                .getBody() // 3. Claims(본문) 추출
                .getSubject(); // 4. Subject(userId) 반환
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
