package com.spring.app.security.jwt;


import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtManager {

    @Value("${jwt.token.secret}")
    private String secretKey;


    public String generateToken(Authentication authResult){

        return Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public String extractTokenFromRequest(HttpServletRequest request){

        
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if ( !Strings.isNullOrEmpty(authorizationHeader ) &&
                authorizationHeader.startsWith("Bearer ") ){


            String token = authorizationHeader.replace("Bearer ","");
            return token;
        }
        return null;

        }
    public String getUsernameFromToken(String token){
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public Set<? extends GrantedAuthority> getGrantedAuthorities(String token){
        Claims claims = getClaims(token);
        
        List<Map<String, String>> authorities = (List<Map<String, String>>) claims.get("authorities");

        Set<SimpleGrantedAuthority> simpleGrantedAuthority = authorities.stream()
                .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                .collect(Collectors.toSet());

        return simpleGrantedAuthority;
    }
    private Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .parseClaimsJws(token).getBody();
    }

}
