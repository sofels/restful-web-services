package com.example.rest.webservices.restfulwebservices.user.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        //aithenticate all requests
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        //if no authentication, a webpage is shown asking for authentication details.
        http.httpBasic(withDefaults());

        //http.csrf().disable();
        return http.build();
    }

}
