package com.mlone23.minibank.Security

import com.mlone23.minibank.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
open class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        println("=== AUTH ===")
        println("Searching email: $email")


        val user = userRepository.findByEmail(email) ?: throw RuntimeException("Email not found")

        println("User found: ${user.email}")
        println("Password from DB: ${user.password}")
        println("Password with prefix: {noop}${user.password}")
        return User.builder()
            .username(user.email)
            .password("{noop}${user.password}")
            .roles("USER")
            .build()
    }
}