package com.skinairvalve.sz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

/**
 * @create on 2023/9/6-7:15 PM
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private static final String[] STATIC_RESOURCE_PATTERNS = new String[]{"/**/*.js","/**/*.css","/**/*.html"};

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests(authorize ->
                        authorize.antMatchers("/api/szUser/**").permitAll()
                                .antMatchers("/api/auth/login","/api/auth/signup").permitAll()
                                .antMatchers("/","/error","/**/swagger-ui*").permitAll()
                                // swagger and api docs config
                                .antMatchers("/swagger*", "/webjars/**", "/swagger-resources/**","/v2/**")
                                .permitAll()
                                .antMatchers(STATIC_RESOURCE_PATTERNS).permitAll()
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(Customizer.withDefaults())
                .exceptionHandling(
                        exception -> exception
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                );
        return http.build();
    }

}
