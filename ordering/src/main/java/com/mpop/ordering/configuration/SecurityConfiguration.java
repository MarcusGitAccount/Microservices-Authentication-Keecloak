package com.mpop.ordering.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.formLogin().disable();

        http.authorizeRequests()
                .anyRequest().permitAll()
                .and().csrf().disable()
                .httpBasic().disable();

        return http.build();
    }
}
