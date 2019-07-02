package com.kys9261.mapsearch.security;

import com.kys9261.mapsearch.model.user.User;
import lombok.Getter;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
public class AuthenticationResult {

    private final String jwtToken;

    private final User user;

    public AuthenticationResult(String jwtToken, User user) {
        checkNotNull(jwtToken, "jwtToken must be provided.");
        checkNotNull(user, "user must be provided.");

        this.jwtToken = jwtToken;
        this.user = user;
    }
}
