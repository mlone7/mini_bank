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
    var balance: Double = 0.0,

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User
){
    constructor(): this(null,"",0.0, User())
    constructor(ownerName: String, balance: Double, user: User) : this (null, ownerName, balance, user)
}