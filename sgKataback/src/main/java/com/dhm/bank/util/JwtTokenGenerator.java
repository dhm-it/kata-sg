package com.dhm.bank.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenGenerator {
    /**
     * Generate a jwt token valid for 1 hour
     *
     * @param u UserDto
     * @return generated token
     */
    public String generateToken(JwtUserDto u) {
        Claims claims = Jwts.claims().setSubject(u.getUsername());
        claims.put("userId", u.getId() + "");
        claims.put("role", u.getRole());
        Date expiration = Date.from(LocalDateTime.now().plusHours(1).toInstant(OffsetDateTime.now().getOffset()));
        String secret = "MIIBOQIBAAJAZjl5XtXoHZglJmNw2LYvmQufweXbv/tmwcPjCKIgYTSOWRkPGjOQ\n" +
                "2V6pqQGKAr5cue1eLklYYkCP6wFzcmTOLQIDAQABAkAyfgMQL9ImBAedjZKoyFx8\n" +
                "WPyBNrszeccIwGv80K/zsvKph+neeeuvtAlpTmbJ/tZAHAdcZ+hqo4Ss+jPipofB\n" +
                "AiEAwQaiIGt4DvdM6dRMEleEQuf9sZyVIb+/byiUYi7xVD0CIQCHky6BPoXnTWEh\n" +
                "z+r7dS87nuDBW0729ev7jASPZvjQsQIhAKzzZZcI1BUMznSBDPwuQEq2lSBsPU91\n" +
                "AHD3+iQTDZa9AiBMpg2YzHxv9POBZkd3uxzlHEH9j13picfNA7BgUttBQQIgakGQ\n" +
                "swuf6UivTBZUR8dkO50dqhV1514Ya0groltR89A=";
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret)
                .setExpiration(expiration)
                .compact();
    }

}