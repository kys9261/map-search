package com.kys9261.mapsearch.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {

    private JWT jwt;

    private String clientSecret="kys9261";


    @Before
    public void init() {
        jwt = new JWT(clientSecret);
    }

    @Test
    public void generateJwtToken() {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("userId", "testAccount1");

        String jwtToken = jwt.generateToken(Jwts.claims(claimsMap));
        Claims claims = jwt.getJwtTokenClaims(jwtToken);

        assertThat(claimsMap.get("userId"), is(claims.get("userId")));
    }
}
