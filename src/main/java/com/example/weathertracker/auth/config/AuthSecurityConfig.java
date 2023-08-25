package com.example.weathertracker.auth.config;

import com.example.weathertracker.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class AuthSecurityConfig {

    private UserService userService;

    @Autowired
    public AuthSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/auth/login", "/auth/register").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                    .loginPage("/auth/login")
                    .loginProcessingUrl("/auth/login")
                    .defaultSuccessUrl("/weather/main_page", true)
                    .permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                .userDetailsService(userService);

        return http.build();
    }

}
