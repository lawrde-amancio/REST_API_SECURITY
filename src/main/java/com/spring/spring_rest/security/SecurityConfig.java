package com.spring.spring_rest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.spring.spring_rest.service.CustomUserDetailsService;

/**
 * Spring Security configuration class defining 
 * authentication, password encoding,
 * and HTTP request authorization rules.
 */
@EnableWebSecurity 
@Configuration 
public class SecurityConfig {

    private CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.setUserDetailsService(userDetailsService);
    }

    /**
     * Configures the HTTP security filter chain, disabling CSRF, 
     * permitting unauthenticated
     * access to {@code /api/auth/**} endpoints, requiring 
     * authentication for all other requests,
     * and enabling HTTP Basic authentication.
     *
     * @param security the {@link HttpSecurity} builder used 
     * to configure web-based security
     * @return the configured {@link SecurityFilterChain} instance
     * @throws Exception if an error occurs while building the 
     * security filter chain
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity security)
        throws Exception {
            return security
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                                        .requestMatchers("/api/auth/**")
                                        .permitAll()
                                        .anyRequest().authenticated()
                                        )
                .httpBasic(Customizer.withDefaults())
                .build();
        }

    /**
     * Creates and exposes a {@link BCryptPasswordEncoder} 
     * bean for hashing and verifying passwords.
     *
     * @return a new {@link BCryptPasswordEncoder} instance
     */
    @Bean 
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Exposes the {@link AuthenticationManager} bean from the provided 
     * Spring Security configuration.
     *
     * @param authentication the {@link AuthenticationConfiguration} 
     * used to retrieve the manager
     * @return the configured {@link AuthenticationManager} instance
     * @throws Exception if an error occurs while retrieving
     *  the authentication manager
     */
    @Bean 
    AuthenticationManager authenticationManger
        (AuthenticationConfiguration authentication) throws Exception {
            return authentication.getAuthenticationManager();
        }

    

    public CustomUserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(
        CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    
}
