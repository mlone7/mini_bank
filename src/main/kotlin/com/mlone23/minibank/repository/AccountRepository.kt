package com.mlone23.minibank.repository

import com.mlone23.minibank.model.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {
    fun findByOwnerEmail(email: String) : List<Account>

    fun findByAccountNumber(accountNumber: Int): Account

    fun existsByAccountNumber(accountNumber: Int): Boolean
}