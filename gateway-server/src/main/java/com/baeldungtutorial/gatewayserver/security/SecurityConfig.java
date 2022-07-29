/*package com.baeldungtutorial.gatewayserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@EnableWebSecurity
@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    public void configureGlobal1(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("ADMIN");
    }

    @Bean
    public SecurityFilterChain gatewayFilterChain(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/book-service/books").permitAll()
                .antMatchers("/eureka/**").hasRole("ADMIN")
                .anyRequest().authenticated().and()
                //.formLogin().and()
                .httpBasic().and()
                .logout().permitAll().and()
                .csrf().disable();
        //http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
        return http.build();
    }
}
*/