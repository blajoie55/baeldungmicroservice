package com.baeldungtutorial.gatewayserver.security;
import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebSecurity
//@EnableWebFluxSecurity
@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
public class SecurityConfig {

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER")
//                .and()
//                .withUser("admin").password("admin").roles("ADMIN");
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .formLogin()
//                .defaultSuccessUrl("/home/index.html", true)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/books/**", "/ratings/**", "/login*", "/").permitAll()
//                .antMatchers("/eureka/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .logout()
//                .and()
//                .csrf().disable();
//    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http
//                .authorizeExchange()
//                .anyExchange().authenticated()
//                .and()
//                .httpBasic().and()
//                .formLogin();
//                //.formLogin().and()
//                //.csrf().disable();
        http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/").permitAll()
                .anyExchange().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin().disable();
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
//    {
//        http
//                .formLogin()
//                .defaultSuccessUrl("/home/index.html", true)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/books/**", "/ratings/**", "/login*", "/").permitAll()
//                .antMatchers("/eureka/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .logout()
//                .and()
//                .csrf().disable();
//        return http.build();
//    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin").roles("ADMIN").build();
        return new MapReactiveUserDetailsService(user, admin);
    }
}