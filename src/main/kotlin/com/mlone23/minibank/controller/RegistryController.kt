package com.mlone23.minibank.controller

import com.mlone23.minibank.model.User
import com.mlone23.minibank.service.RegisterService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
@Tag(name = "Accounts", description = "ss")
class RegistryController(val registerService: RegisterService) {

    @GetMapping("/")
    fun home(): String {
        return "redirect:/swagger-ui.html"
    }

    @PostMapping("/register")
    fun registryController(@RequestParam email: String, @RequestParam password: String, @RequestParam name: String): User {
        val user = registerService.registryUser(email, password, name)
        return user
    }
    //
}