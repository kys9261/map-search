package com.kys9261.mapsearch.security;

import lombok.Getter;

@Getter
public class AuthenticationRequest {

    private String userId;
    
    private String password;

    public AuthenticationRequest() {

    }

    public AuthenticationRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
