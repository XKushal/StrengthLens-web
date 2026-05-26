package org.strengthlens.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(requests -> requests
                .requestMatchers("/api/oauth2/v1/public").permitAll().anyRequest().authenticated())
                .oauth2Login(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
