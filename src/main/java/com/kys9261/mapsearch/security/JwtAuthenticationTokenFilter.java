package com.kys9261.mapsearch.security;

import com.kys9261.mapsearch.exception.AccessDeniedException;
import com.kys9261.mapsearch.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class JwtAuthenticationTokenFilter extends GenericFilterBean {

    @Autowired
    private JWT jwt;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                String token = request.getHeader("x-access-token") == null ? request.getHeader("Authorization") : request.getHeader("x-access-token");
                Claims claims = jwt.getJwtTokenClaims(token);
                if (claims != null) {
                    String userid = String.valueOf(claims.get("userId"));
                    int userSeq = Integer.parseInt(String.valueOf(claims.get("userSeq")));

                    if (userid != null) {
                        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
                        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

                        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(userid, null, userSeq, roles);
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    } else {
                        throw new AccessDeniedException();
                    }
                }
            } catch (Exception e) {
                log.warn("Jwt processing failed: {}", e.getMessage());
            }
        } else {
            log.debug("SecurityContextHolder not populated with security token, as it already contained: '{}'",
                    SecurityContextHolder.getContext().getAuthentication());
        }


        chain.doFilter(request, response);
    }



}
