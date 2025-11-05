package com.mlone23.minibank.service

import com.mlone23.minibank.model.Account
import com.mlone23.minibank.repository.AccountRepository
import com.mlone23.minibank.repository.UserRepository
import org.springframework.stereotype.Service
import kotlin.random.Random

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

    fun deposit(amount: Double, accountNumber: Int): Account {
        val account = accountRepository.findByAccountNumber(accountNumber)

        account.balance += amount
        return accountRepository.save(account)
    }


    fun transactions(accountNumberToTransfer: Int, accountNumberFromTransfer: Int, amount: Double): Account {

        val from = accountRepository.findByAccountNumber(accountNumberFromTransfer)
        val to = accountRepository.findByAccountNumber(accountNumberToTransfer)

        from.balance -= amount
        to.balance += amount


        accountRepository.save(from)
        return accountRepository.save(to)
    }


    fun accountNumber(): Int {

        while (true) {
            var accountNumber = 0

            for (i in 1..5) {
                val digit = Random.nextInt(1, 9)
                accountNumber = accountNumber * 10 + digit
            }

            if (!accountRepository.existsByAccountNumber(accountNumber)) {
                return accountNumber
            }
        }
    }
}