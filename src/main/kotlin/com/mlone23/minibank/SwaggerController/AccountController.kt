package com.mlone23.minibank.SwaggerController

import com.mlone23.minibank.model.Account
import com.mlone23.minibank.repository.AccountRepository
import com.mlone23.minibank.repository.UserRepository
import com.mlone23.minibank.service.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
//@RequestMapping("/api")
class AccountController(
    val account: AccountService,
    val userRepository: UserRepository,
    val accountRepository: AccountRepository,
    val accountService: AccountService
) {

    @PostMapping("/createAccount")
    fun createAccount(): Account {

        val authentication = SecurityContextHolder.getContext().authentication
        val email = authentication.name

        val user = userRepository.findByEmail(email) ?: throw RuntimeException("User not found")

        val newAccount = Account(email, 0.0, account.accountNumber(), user)

        return accountRepository.save(newAccount)
    }

    @PostMapping("/transactions")
    fun transactions(@RequestParam accountFromNumber: Int,
                     @RequestParam accountToNumber: Int,
                     @RequestParam amount: Double,
                     model: Model): ResponseEntity<String> {

        val transactions = account.transactions(accountToNumber, accountFromNumber, amount)
        return ResponseEntity.ok("Transactions completed")
    }

    @PostMapping("/dashboard/deposit")
    fun deposit(@RequestParam accountId: Long, @RequestParam amount: Double): ResponseEntity<String> {
        val account = accountService.deposit(amount, accountId)

        return ResponseEntity.ok("Deposit is successful")
    }
}