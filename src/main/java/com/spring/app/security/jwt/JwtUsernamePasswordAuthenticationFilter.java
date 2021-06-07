package com.spring.app.security.jwt;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtManager jwtManager;

    public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtManager jwtManager) {
        this.authenticationManager = authenticationManager;
        this.jwtManager = jwtManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try{
            RequestUsernamePassword usernamePasswordRequest = new ObjectMapper().readValue(
                            request.getInputStream(),
                            RequestUsernamePassword.class
                    );

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                usernamePasswordRequest.getUsername(),
                usernamePasswordRequest.getPassword()
            );

            return authenticationManager.authenticate(authentication);

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    // here we create token
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String token = jwtManager.generateToken(authResult);
        response.addHeader("Authorization", "Bearer " + token);
        new ObjectMapper().writeValue(response.getOutputStream(),"Bearer " + token);

    }
}
