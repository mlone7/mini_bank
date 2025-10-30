package com.mlone23.minibank.model

import jakarta.persistence.*

@Entity
@Table(name = "accounts")
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var ownerName: String,

    @Column(nullable = false)
    var balance: Double = 0.0
)
