package com.example.RateLimiter.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;

public class JWTUtils {


    private static final String SECRET_KEY = "Speer#1x9!0#3";

    public static String createJwt(String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        long expirationTime = nowMillis + 3600000 * 24; // 1 day
        Date expirationDate = new Date(expirationTime);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Claims extractSubject(String jwtToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwtToken)
                .getBody();

        return claims;
    }

    public static String refreshJWT(String existingToken) {
        return createJwt(extractSubject(existingToken).getSubject());
    }

    public static boolean isExpired (Claims claims) {
        if (claims.getExpiration().before(new Date()))
            return false;
        return true;
    }
}

