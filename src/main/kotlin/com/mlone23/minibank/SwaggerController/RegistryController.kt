package com.mlone23.minibank.SwaggerController

import com.mlone23.minibank.model.User
import com.mlone23.minibank.service.RegisterService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
@Tag(name = "Accounts", description = "ss")
class RegistryController(
    private val registerService: RegisterService
) {

    @GetMapping("/")
    fun home(authentication: Authentication?): String {
        return "redirect:/swagger-ui.html"
    }

    @PostMapping("/register")
    fun registryController(@RequestParam email: String,
                           @RequestParam password: String,
                           @RequestParam name: String
    ): User {
        return registerService.registryUser(email, password, name)
    }
}