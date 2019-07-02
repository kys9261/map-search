package com.kys9261.mapsearch.config;

import com.kys9261.mapsearch.security.JWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Value("${jwt.token.clientSecret}")
    private String clientSecret;

    @Bean
    public JWT jwt() {
        return new JWT(clientSecret);
    }
}
