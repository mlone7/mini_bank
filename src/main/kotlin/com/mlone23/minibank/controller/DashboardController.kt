package com.mlone23.minibank.controller

import com.mlone23.minibank.repository.AccountRepository
import com.mlone23.minibank.service.AccountService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class DashboardController(
    val accountService: AccountService,
    val accountRepository: AccountRepository
) {

    @GetMapping("/dashboard")
    fun dashboard(model: Model): String {
        val authentication = SecurityContextHolder.getContext().authentication
        val username = authentication.name

        val account = accountRepository.findByOwnerEmail(username)

        model.addAttribute("account", account)
        model.addAttribute("balance", account.balance)
        model.addAttribute("name", account.ownerEmail)
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