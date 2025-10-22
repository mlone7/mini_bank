package com.mlone23.minibank.controller

import com.mlone23.minibank.model.User
import com.mlone23.minibank.repository.UserRepository
import com.mlone23.minibank.service.RegisterService
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")

class RegistryController(val user: User, val registerService: RegisterService) {

    fun registryController(@RequestBody email: String, @RequestBody password: String, @RequestBody name: String): User{
        val user = registerService.registryUser(email, password, name)
        return user
    }
}