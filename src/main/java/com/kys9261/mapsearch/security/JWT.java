package com.kys9261.mapsearch.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

@Component
public class JWT {

    private String clientSecret;

    public JWT() { }

    public JWT(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String generateToken(Claims claims) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setHeaderParam("typ", "JWT");
        jwtBuilder.setHeaderParam("issueDate", System.currentTimeMillis());
        jwtBuilder.claim("userId", claims.get("userId"));
        jwtBuilder.claim("userSeq", claims.get("userSeq"));
        jwtBuilder.signWith(SignatureAlgorithm.HS256, clientSecret);
        return jwtBuilder.compact();
    }

    public Claims getJwtTokenClaims(String token) {
        if(token != null) {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(clientSecret).parseClaimsJws(token);
            return claims.getBody();
        }

        return null;
    }
}
