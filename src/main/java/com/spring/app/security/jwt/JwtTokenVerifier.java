package com.spring.app.security.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// in this class we verify token sent by client
public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtManager jwtManager;

    public JwtTokenVerifier(JwtManager jwtManager) {
        this.jwtManager = jwtManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String token = jwtManager.extractTokenFromRequest(request);

        if(token == null){
            chain.doFilter(request, response);
            return;
        }

        try {
            String username = jwtManager.getUsernameFromToken(token);

            Set<? extends GrantedAuthority> SimpleGrantedAuthority =
                    jwtManager.getGrantedAuthorities(token);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    SimpleGrantedAuthority
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Token %s cannot be truest", token));
        }
        chain.doFilter(request, response);
    }
}
