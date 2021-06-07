package com.spring.app.security.config;

import com.spring.app.security.auth.AppUserService;
import com.spring.app.security.jwt.JwtManager;
import com.spring.app.security.jwt.JwtTokenVerifier;
import com.spring.app.security.jwt.JwtUsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final AppUserService appUserService;
    private final JwtManager jwtManager;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, AppUserService appUserService, JwtManager jwtManager) {
        this.passwordEncoder = passwordEncoder;
        this.appUserService = appUserService;
        this.jwtManager = jwtManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .addFilter(new JwtUsernamePasswordAuthenticationFilter(authenticationManager(), jwtManager))
                .addFilterAfter(new JwtTokenVerifier(jwtManager), JwtUsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/v2/api-docs", "/api-docs",
                        "/configuration/ui", "/configuration/security",
                        "/swagger-ui/**", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/users/register").permitAll()
                .antMatchers(HttpMethod.POST, "/rest/api/v1/login").permitAll()
                .antMatchers(HttpMethod.POST, "/rest/api/v1/user").permitAll()

                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;

    }




}
