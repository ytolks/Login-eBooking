package com.example.eBookingAppointment.config;

import com.example.eBookingAppointment.service.CustomAuthenticationProviderService;
import com.example.eBookingAppointment.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableMethodSecurity
public class WebSecurityConfig {

    private CustomAuthenticationProviderService authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic();
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        (requests) -> {
                            requests.
                                    requestMatchers("/user/registration/**").permitAll()
                                   // .requestMatchers("/user/list").hasRole("ROLE_USER")
                                    .anyRequest().authenticated();
                        }
                );
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    }

}
