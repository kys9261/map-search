package com.kys9261.mapsearch.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kys9261.mapsearch.model.response.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ApiResult forbiddenResult = new ApiResult("Access Denied", HttpStatus.UNAUTHORIZED);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader("content-type", "application/json");
        response.getWriter().write(mapper.writeValueAsString(forbiddenResult));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
