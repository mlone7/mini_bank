package com.mlone23.minibank.controller

import com.mlone23.minibank.repository.AccountRepository
import com.mlone23.minibank.service.AccountService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@Controller
//@RequestMapping("/api")
class DashboardController(
    val accountService: AccountService,
    val accountRepository: AccountRepository
) {

    @GetMapping("/dashboard")
    fun dashboard(model: Model): String {

        return "dashboard"
    }


    @PostMapping("/dashboard/{accountId}/deposit")
    fun deposit(@PathVariable accountId: Long, @RequestParam amount: Double, model: Model): String {
        val account = accountService.deposit(amount, accountId)
        model.addAttribute("account", account)
        model.addAttribute("balance", account.balance)
        model.addAttribute("name", account.ownerEmail)
        model.addAttribute("success", "Successfully deposited $amount")
        return "dashboard"
    }
}