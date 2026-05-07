package com.example.HotelmanagmentSystem.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())

                .httpBasic(httpBasic -> httpBasic.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/users/**").hasAuthority("ROLE_USER")
                        .requestMatchers("/api/rooms/**").hasAuthority("ROLE_USER")
                        .requestMatchers("/api/bookings/**").hasAuthority("ROLE_USER")
                        .requestMatchers("/api/payments/**").hasAuthority("ROLE_USER")
                        .requestMatchers("/api/staff/**").hasAuthority("ROLE_USER")
                        .requestMatchers("/swagger-ui/**").permitAll()      // ✅ Add kar
                        .requestMatchers("/api-docs/**").permitAll()         // ✅ Add kar
                        .requestMatchers("/v3/api-docs/**").permitAll()
                       // .anyRequest().authenticated()
                        .anyRequest().permitAll()

                )

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}