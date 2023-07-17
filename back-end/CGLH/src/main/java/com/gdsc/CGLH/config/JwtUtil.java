package com.gdsc.CGLH.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtUtil {
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24시간동안 유효
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static String extractloginId(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public  static String getUserIdFromJWT(String token) {
        // 'secretKey'는 실제 서비스에서는 외부에 노출되지 않도록 관리해야 하는 비밀 키입니다.
        // 이 키는 JWT 토큰 생성시 사용한 키와 동일해야 합니다.
        String secretKey = "secretKey12345678901234567890"; // 예시 키입니다. 실제로는 이보다 긴, 안전한 키를 사용해야 합니다.

        // 해당 키로 JWT 토큰을 디코딩합니다.
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        Claims claims = jwsClaims.getBody();

        // JWT 토큰의 payload에서 사용자 ID를 가져옵니다.
        String userId = claims.getSubject();

        return userId;
    }
}

