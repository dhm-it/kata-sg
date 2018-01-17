package com.dhm.bank.util;

import com.dhm.bank.clients.ClientService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
@Component
public class JwtTokenValidator {
  private final ClientService clientService ;
    @Autowired
    public JwtTokenValidator(ClientService clientService) {
        this.clientService = clientService;
    }


    /**
     * Extract user data from token
     *
     * @param token
     * @return JwtUserDto
     */
    public JwtUserDto parseToken(String token) {
        JwtUserDto user = null;
        Date referenceTime = Date.from(LocalDateTime.now().toInstant(OffsetDateTime.now().getOffset()));
        String secret = "MIIBOQIBAAJAZjl5XtXoHZglJmNw2LYvmQufweXbv/tmwcPjCKIgYTSOWRkPGjOQ\n" +
                "2V6pqQGKAr5cue1eLklYYkCP6wFzcmTOLQIDAQABAkAyfgMQL9ImBAedjZKoyFx8\n" +
                "WPyBNrszeccIwGv80K/zsvKph+neeeuvtAlpTmbJ/tZAHAdcZ+hqo4Ss+jPipofB\n" +
                "AiEAwQaiIGt4DvdM6dRMEleEQuf9sZyVIb+/byiUYi7xVD0CIQCHky6BPoXnTWEh\n" +
                "z+r7dS87nuDBW0729ev7jASPZvjQsQIhAKzzZZcI1BUMznSBDPwuQEq2lSBsPU91\n" +
                "AHD3+iQTDZa9AiBMpg2YzHxv9POBZkd3uxzlHEH9j13picfNA7BgUttBQQIgakGQ\n" +
                "swuf6UivTBZUR8dkO50dqhV1514Ya0groltR89A=";

        Claims body = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        Date expirationTime = body.getExpiration();
        if (expirationTime == null || body.getExpiration().before(referenceTime)) {
            throw new BadCredentialsException("Invalid Token: token expired");
        }
        user = new JwtUserDto();
        user.setUsername(body.getSubject());
        user.setId(body.get("userId").toString());
        List<String> listOfRole = (List<String>) body.get("role");

        user.setRole(listOfRole);
        if (clientService.getClientById(user.getId()) == null) {
            throw new BadCredentialsException("user Not Found ");
        }

        return user;
    }


}
