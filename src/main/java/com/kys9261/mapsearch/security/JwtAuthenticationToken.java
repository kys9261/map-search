package com.kys9261.mapsearch.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private Object principal;

    private String credentials;

    private int userSeq;

    public JwtAuthenticationToken(String principal, String credentials) {
        super(null);
        super.setAuthenticated(false);

        this.principal = principal;
        this.credentials = credentials;
    }

    JwtAuthenticationToken(Object principal, String credentials, int userSeq, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        super.setAuthenticated(true);

        this.principal = principal;
        this.credentials = credentials;
        this.userSeq = userSeq;
    }


    @Override
    public String getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public int getUserSeq() {
        return userSeq;
    }
}
