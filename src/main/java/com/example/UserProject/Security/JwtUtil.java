package com.example.UserProject.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY_256 = "6eohufRhyfW13H6nnj2SowF6KE6hSqB8";

    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_256.getBytes());

    public String tokenGenerator(UserDetails user){
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SECRET_KEY)
                .compact();
    }

}
