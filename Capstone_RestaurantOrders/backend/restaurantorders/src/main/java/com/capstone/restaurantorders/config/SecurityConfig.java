package com.capstone.restaurantorders.config;

import com.capstone.restaurantorders.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.*;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> {})
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth

                        // 🔓 PUBLIC (NO LOGIN REQUIRED)
                        .requestMatchers(
                                "/api/users/register",
                                "/api/users/login",
                                "/api/restaurants/**",
                                "/api/categories/**",
                                "/api/menu-items/category/**"
                        ).permitAll()

                        // 👤 USER (CUSTOMER)
                        .requestMatchers(
                                "/api/cart/**",
                                "/api/orders/place",
                                "/api/orders/user/**",
                                "/api/orders/cancel/**"
                        ).hasAuthority("ROLE_USER")

                        // 👨‍🍳 OWNER ONLY
                        .requestMatchers(
                                "/api/restaurants/**",
                                "/api/categories/**",
                                "/api/menu-items/**",
                                "/api/orders/all",
                                "/api/orders/updateStatus/**"
                        ).hasAuthority("ROLE_RESTAURANT_OWNER")

                        .anyRequest().authenticated()
                )

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}