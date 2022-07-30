package com.baeldungtutorial.gatewayserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

//@EnableWebSecurity
//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //@Autowired
    public void configureGlobal1(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests()
                .antMatchers("/book-service/books").permitAll()
                .antMatchers("/eureka/**").hasRole("ADMIN")
                .anyRequest().authenticated().and()
                .formLogin().and()
                .httpBasic().and()
                .logout().permitAll().and()
                .csrf().disable();*/
        /*http
                //.formLogin()
                //.defaultSuccessUrl("/home/index.html", true)
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/books/**", "/ratings/**", "/login*", "/").permitAll()
                .antMatchers("/eureka/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .logout()
                .and()
                .csrf().disable();*/
        http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
    }

    //@Bean
    public SecurityFilterChain gatewayFilterChain(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/book-service/books").permitAll()
                .antMatchers("/eureka/**").hasRole("ADMIN")
                .anyRequest().authenticated().and()
                .formLogin().and()
                .httpBasic().and()
                .logout().permitAll().and()
                .csrf().disable();
        //http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
        return http.build();
    }
}