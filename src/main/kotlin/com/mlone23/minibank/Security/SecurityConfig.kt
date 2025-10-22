package com.mlone23.minibank.Security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
open class SecurityConfig {


    @Bean
    open fun securityFilterChain(http: HttpSecurity) : SecurityFilterChain {
        http
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { auth -> auth.requestMatchers(
                "/swagger-ui/html",
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/api/register",
                "/webjars"
            ).permitAll()
                .requestMatchers("/api/register").permitAll()
                .requestMatchers("/api/**").authenticated().anyRequest().authenticated()
            }
            .httpBasic { }

        return http.build()
    }
}