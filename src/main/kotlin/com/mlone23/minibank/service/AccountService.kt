package com.mlone23.minibank.service

import com.mlone23.minibank.model.Account
import com.mlone23.minibank.repository.AccountRepository
import com.mlone23.minibank.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AccountService(
    val userRepository: UserRepository,
    val accountRepository: AccountRepository
) {

    fun getAccount(accountId: Long):Account {
        return accountRepository.findById(accountId).orElseThrow {
            RuntimeException("Error")
        }
    }

    fun deposit(amount: Double, id: Long): Account {
        val account = accountRepository.findById(id).orElseThrow { RuntimeException("Error")}

        account.balance += amount
        return accountRepository.save(account)
    }
}