package com.baeldungtutorial.ratingservice.security;

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
public class SecurityConfig {//extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal1(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication();
    }

    /*@Override
    protected void configure(HttpSecurity http) throws Exception
    {
        configureCommon(http);
    }*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        return configureCommon(http).build();
    }

    private HttpSecurity configureCommon(HttpSecurity http) throws Exception
    {
        http.httpBasic()
                .disable()
                .authorizeRequests()
                .regexMatchers("^/ratings\\?bookId.*$").authenticated()
                //.antMatchers(HttpMethod.POST, "/ratings").authenticated()
                .antMatchers(HttpMethod.POST, "/ratings").permitAll()
                .antMatchers(HttpMethod.PATCH, "/ratings/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/ratings/*").hasRole("ADMIN")
                //.antMatchers(HttpMethod.GET, "/ratings").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/ratings").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf()
                .disable();
        //http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
        return http;
    }
}
