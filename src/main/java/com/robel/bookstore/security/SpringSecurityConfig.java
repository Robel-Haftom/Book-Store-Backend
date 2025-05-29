package com.robel.bookstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private AuthFilterService authFilterService;

    @Bean
    public SecurityFilterChain customSecurityFilter(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers("/api/v1/public/**").permitAll()
                                .requestMatchers("/api/v1/common/**").authenticated()
                                .requestMatchers("/api/v1/users/**").hasRole("USER")
                                .requestMatchers("/api/v1/admin/**").hasAnyRole("ADMIN", "SUPER_ADMIN")
                                .requestMatchers("/api/v1/super-admin/**").hasRole("SUPER_ADMIN")
                                .anyRequest().authenticated())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authFilterService, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource(){
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(Arrays.asList(
                "http://localhost:5173",
                "http://localhost:5174"
                ));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(Arrays.asList("Set-Cookie", "Authorization"));

        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return corsConfigurationSource;
    }
}
