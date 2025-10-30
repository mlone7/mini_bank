package com.mlone23.minibank.controller

import com.mlone23.minibank.service.AccountService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class DashboardController(
    private val accountService: AccountService
) {

    @GetMapping("/dashboard")
    fun showDashboard(@RequestParam name: String, model: Model): String {
        val account = accountService.createAccount(name)
        model.addAttribute("name", name)
        model.addAttribute("balance", account.balance)
        return "dashboard"
    }

    @PostMapping("/dashboard/topup")
    fun topUpBalance(
        @RequestParam name: String,
        @RequestParam amount: Double,
        model: Model
    ): String {
        val account = accountService.addBalance(name, amount)
        model.addAttribute("name", name)
        model.addAttribute("balance", account.balance)
        model.addAttribute("success", "Balance successfully updated!")
        return "dashboard"
    }
}
