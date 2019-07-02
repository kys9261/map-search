package com.kys9261.mapsearch.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kys9261.mapsearch.security.JWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    private String userId;

    @JsonIgnore
    private String password;

    private LocalDateTime createAt;

    public void login(PasswordEncoder passwordEncoder, String password) {
        if(!passwordEncoder.matches(password, this.password)) {
            throw new IllegalArgumentException("Password is not equal!");
        }
    }

    public String generateJwtToken(JWT jwt) {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("userId", getUserId());
        claimsMap.put("userSeq", getSeq());
        Claims claims = Jwts.claims(claimsMap);
        return jwt.generateToken(claims);
    }
}

