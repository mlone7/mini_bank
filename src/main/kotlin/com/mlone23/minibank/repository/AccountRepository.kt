package com.mlone23.minibank.repository

import com.mlone23.minibank.model.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {
}