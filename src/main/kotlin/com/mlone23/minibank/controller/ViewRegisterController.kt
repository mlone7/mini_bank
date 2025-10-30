package com.mlone23.minibank.controller

import com.mlone23.minibank.service.RegisterService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class ViewRegisterController(
    private val registerService: RegisterService
) {

    @GetMapping("/register")
    fun showRegisterPage(): String = "register"

    @PostMapping("/register")
    fun handleRegister(
        @RequestParam email: String,
        @RequestParam name: String,
        @RequestParam password: String,
        model: Model
    ): String {
        return try {
            registerService.registryUser(email, password, name)
            model.addAttribute("success", "User registered successfully!")
            "redirect:/dashboard"
        } catch (e: Exception) {
            model.addAttribute("error", "Error: ${e.message}")
            "register"
        }
    }
}
