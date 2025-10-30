package com.mlone23.minibank.Service

import com.mlone23.minibank.model.User
import com.mlone23.minibank.repository.UserRepository
import com.mlone23.minibank.service.RegisterService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.crypto.password.PasswordEncoder
import kotlin.test.assertEquals


@ExtendWith(MockitoExtension::class)
class CreateUserTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var passwordEncoder: PasswordEncoder

    @InjectMocks
    private lateinit var registerService: RegisterService

    @Test
    fun `should register user with encode password`() {
        val email = "test@gmail.com"
        val name = "testUser"
        val password = "testPassword"
        val encodedPassword = "encodedPassword"



        val expectedUser = User(email, name, encodedPassword)

        `when`(passwordEncoder.encode(password)).thenReturn(encodedPassword)
        `when`(userRepository.save(any(User::class.java))).thenReturn(expectedUser)

        val result = registerService.registryUser(email, name, password)

        assertEquals(expectedUser, result)
        verify(passwordEncoder).encode(password)
        verify(userRepository).save(any(User::class.java))
    }
}