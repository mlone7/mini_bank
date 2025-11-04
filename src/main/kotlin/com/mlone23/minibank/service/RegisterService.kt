package com.mlone23.minibank.service

import com.mlone23.minibank.model.Account
import com.mlone23.minibank.model.User
import com.mlone23.minibank.repository.AccountRepository
import com.mlone23.minibank.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class RegisterService(
    val userRepository: UserRepository,
    val accountRepository: AccountRepository,
    val passwordEncoder: PasswordEncoder
) {

    fun registryUser(email: String, name: String, password: String): User {

        val encodePassword = passwordEncoder.encode(password)

        val newUser = User(email, name, encodePassword)
        val savedUser = userRepository.save(newUser)

        val newAccount = Account(name, 0.0, savedUser)
        accountRepository.save(newAccount)

        return savedUser
    }





}