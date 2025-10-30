package com.mlone23.minibank.service

import com.mlone23.minibank.model.User
import com.mlone23.minibank.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class RegisterService(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder
) {

    fun registryUser(email: String, name: String, password: String): User {

        val encodePassword = passwordEncoder.encode(password)

        val newUser = User(email, name, encodePassword)
        return userRepository.save(newUser)
    }
}