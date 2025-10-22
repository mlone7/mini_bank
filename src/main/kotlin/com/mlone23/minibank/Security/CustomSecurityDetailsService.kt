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
        val user = userRepository.findByEmail(email) ?: throw RuntimeException("Email not found")
        return User.builder()
            .username(user.email)
            .password(user.password)
            .roles("USER")
            .build()
    }
}