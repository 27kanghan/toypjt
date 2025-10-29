package com.example.toypjt.jwt;

//import lombok.Value;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret.ket}")
    private String secretKey;

    //JWT 서명에 사용할 키 객체
    private Key key;

    //암호화 알고리즘
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    //객체 생성 후 초기화 ( 비밀키 base64 인코딩 및 key객체 생성
    @PostConstruct
    public void init() {
        byte bytes[] = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    /***
     * AccessToken 생성
     * @param username 사용자 이름
     * @return 생성된 토큰 문자열
     */
    public String createAccessToken(String username){
        Date date = new Date();
        long TOKEN_TIME = 60*60*1000L; // 1시간

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(date.getTime() + TOKEN_TIME))
                .setIssuedAt(date) // 발급 시간 설정
                .signWith(key, signatureAlgorithm) //비밀키와 알고리즘으로 서명함
                .compact();//문자열로 압축
    }

    /***
     * 토큰 검증
     * @param token 검증할 토큰 문자열
     * @return 유효하면 true 아니면 false.
     */
    public boolean validateToken(String token) {
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    /***
     * 토큰에서 사용자 정보(claims) 추출
     * @param token 토큰 문자열
     * @return 사용자 정보가 담긴 claims 객체
     */
    public Claims getUserInfoFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}
