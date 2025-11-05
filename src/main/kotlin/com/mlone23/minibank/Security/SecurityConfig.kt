package com.mlone23.minibank.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
open class SecurityConfig(
    private val userDetailsService: CustomUserDetailsService
) {



    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    open fun securityFilterChain(http: HttpSecurity) : SecurityFilterChain {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests { auth -> auth
                .requestMatchers(
                    "/api/register",
                    "/register",
                    "/login",
                    "/api/debug/swagger-config",
                    "/api/logout",
                    "/swagger-ui/html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                ).permitAll().anyRequest().authenticated()
            }
            .httpBasic {  }
            .formLogin { form -> form.defaultSuccessUrl("/dashboard", true) }
            .userDetailsService(userDetailsService)

        return http.build()
    }
}