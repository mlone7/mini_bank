package com.mlone23.minibank.Security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
open class SecurityConfig {


    @Bean
    open fun securityFilterChain(http: HttpSecurity) : SecurityFilterChain {
        http
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { auth -> auth.requestMatchers(
                "/api/register",
                "/api/login",
                "/swagger-ui/**",
                "/v3/api-docs/**"
            ).permitAll()
                .anyRequest().authenticated()
            }
            .httpBasic { basic -> basic.realmName("Mini-Bank API")
            }

        return http.build()
    }
}