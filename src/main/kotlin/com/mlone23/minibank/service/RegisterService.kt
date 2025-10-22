package com.mlone23.minibank.service

import com.mlone23.minibank.model.User
import com.mlone23.minibank.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class RegisterService(val userRepository: UserRepository) {

    fun registryUser(email: String, name: String, password: String): User {
        val newUser = User(email, name, password)
        return userRepository.save(newUser)
    }



}