package com.mlone23.minibank.service

import com.mlone23.minibank.model.Account
import org.springframework.stereotype.Service
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional

@Service
class AccountService(
    private val entityManager: EntityManager
) {

    fun getAccountByName(name: String): Account? {
        val query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.ownerName = :name", Account::class.java
        )
        query.setParameter("name", name)
        return query.resultList.firstOrNull()
    }

    @Transactional
    fun createAccount(name: String): Account {
        val existing = getAccountByName(name)
        if (existing != null) return existing

        val acc = Account(ownerName = name, balance = 0.0)
        entityManager.persist(acc)
        return acc
    }

    @Transactional
    fun addBalance(name: String, amount: Double): Account {
        val acc = getAccountByName(name) ?: createAccount(name)
        acc.balance += amount
        entityManager.merge(acc)
        return acc
    }
}
