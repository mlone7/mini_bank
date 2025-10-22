package com.mlone23.minibank.repository

import com.mlone23.minibank.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long>{
}