package com.kys9261.mapsearch.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kys9261.mapsearch.model.response.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ApiResult forbiddenResult = new ApiResult("Access Denied", HttpStatus.FORBIDDEN);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setHeader("content-type", "application/json");
        response.getWriter().write(mapper.writeValueAsString(forbiddenResult));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
