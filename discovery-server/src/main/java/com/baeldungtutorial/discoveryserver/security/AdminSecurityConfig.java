package com.baeldungtutorial.discoveryserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AdminSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        /*http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").hasRole("ADMIN")
                .antMatchers("/info", "/health").authenticated()
                .anyRequest().denyAll().and()
                .csrf().disable();*/
        http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
        return http.build();
    }
}
