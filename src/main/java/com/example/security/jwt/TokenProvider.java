package com.example.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class TokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    public String createToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 6 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //kiểm tra nếu token hết hạn
    private Boolean isTokenExpired(String token) {
        final Date expiration = getAllClaimsFromTokens(token).getExpiration();
        return expiration.before(new Date());
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = this.getAllClaimsFromTokens(token).getSubject();
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Claims getAllClaimsFromTokens(String requestTokenHeader) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(requestTokenHeader)
                    .getBody();
        } catch (Exception e) {
            log.error("Could not get all claims Token from passed token");
            claims = null;
        }
        return claims;
    }
}
