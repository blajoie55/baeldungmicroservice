package com.baeldungtutorial.discoveryserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("discUser").password("{noop}discPassword").roles("SYSTEM");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and().requestMatchers().antMatchers("/eureka/**").and().authorizeRequests().antMatchers("/eureka/**").hasRole("SYSTEM").anyRequest().denyAll().and().httpBasic().and().csrf()
                .disable();
    }

    //@Bean
    public SecurityFilterChain secFilterChain(HttpSecurity http) throws Exception
    {
        /*http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and()
                .requestMatchers().antMatchers("/eureka/**").and()
                .authorizeRequests()
                .antMatchers("/eureka/**").hasRole("SYSTEM")
                .anyRequest().denyAll().and()
                .httpBasic().and()
                .csrf().disable();*/
        http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
        return http.build();
    }
}
