package com.baeldungtutorial.discoveryserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@Order(1)
public class SecurityConfig {

    @Autowired
    public void configureGlobal1(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication().withUser("discUser").password("discPassword").roles("SYSTEM");
    }

    @Bean
    public SecurityFilterChain secFilterChain(HttpSecurity http) throws Exception
    {
        /*http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and()
                .requestMatchers().antMatchers("/eureka/**").and()
                .authorizeRequests()
                .antMatchers("/eureka/**").hasRole("SYSTEM")
                .anyRequest().denyAll().and()
                .httpBasic().and()
                .csrf().disable();*/
        http.authorizeRequests().anyRequest().permitAll().and().httpBasic().and().csrf().disable();
        return http.build();
    }
}
