package com.mlone23.minibank.service

import com.mlone23.minibank.model.User
import com.mlone23.minibank.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class RegisterService(val userRepository: UserRepository) {

    fun registryUser(email: String, password: String, name: String): User{
        val newUser = User(email, password, name)

        return userRepository.save(newUser)

    }


}