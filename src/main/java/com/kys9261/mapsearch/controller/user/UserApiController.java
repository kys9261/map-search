package com.kys9261.mapsearch.controller.user;

import com.kys9261.mapsearch.model.response.ApiResult;
import com.kys9261.mapsearch.security.AuthenticationRequest;
import com.kys9261.mapsearch.security.AuthenticationResult;
import com.kys9261.mapsearch.security.JwtAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ApiResult<AuthenticationResult> login(@RequestBody AuthenticationRequest authenticationRequest) {
        String userId = authenticationRequest.getUserId();
        String password = authenticationRequest.getPassword();

        JwtAuthenticationToken token = new JwtAuthenticationToken(userId, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ApiResult<>((AuthenticationResult) authentication.getDetails());
    }
}
