package com.kys9261.mapsearch.security;

import com.kys9261.mapsearch.service.user.UserService;
import com.kys9261.mapsearch.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private JWT jwt;

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        User user = userService.login(authentication.getName(),authentication.getCredentials().toString());
        JwtAuthenticationToken authenticated =
                new JwtAuthenticationToken(user.getUserId(), null, user.getSeq(), createAuthorityList("ROLE_USER"));

        authenticated.setDetails(new AuthenticationResult(user.generateJwtToken(jwt), user));
        return authenticated;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}
