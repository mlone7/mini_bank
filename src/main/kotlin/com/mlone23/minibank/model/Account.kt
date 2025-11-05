package com.mlone23.minibank.model

import jakarta.persistence.*

@Entity
@Table(name = "accounts")
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var ownerEmail: String,

    @Column(nullable = false)
    var balance: Double = 0.0,

    @Column(nullable = false)
    var accountNumber: Int,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User
){
    constructor(): this(null,"",0.0, 0, User())
    constructor(ownerEmail: String, balance: Double, accountNumber: Int,user: User)
            : this (null, ownerEmail, balance, accountNumber, user)
}