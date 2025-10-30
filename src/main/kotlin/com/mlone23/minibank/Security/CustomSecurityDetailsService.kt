package com.mlone23.minibank.security

import com.mlone23.minibank.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {

        println("=== Start ===")


        val user = userRepository.findByEmail(email) ?: throw RuntimeException("Email: ${email} not found")
        println("user with email ${user.email} authenticated")
        return User.builder()
            .username(user.email)
            .password(user.password)
            .roles("USER")
            .build()
    }
}