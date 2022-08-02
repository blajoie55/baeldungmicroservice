package com.baeldungtutorial.bookservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    public void configureGlobal1(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.httpBasic()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/books").permitAll()
                .antMatchers(HttpMethod.GET, "/books/*").permitAll()
                //.antMatchers(HttpMethod.POST, "/books").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/books").permitAll()
                .antMatchers(HttpMethod.PATCH, "/books/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/books/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf()
                .disable();
        return http.build();
    }
}
