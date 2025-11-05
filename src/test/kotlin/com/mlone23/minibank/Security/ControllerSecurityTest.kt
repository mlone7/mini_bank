package com.mlone23.minibank.Security

import com.mlone23.minibank.SwaggerController.RegistryController
import com.mlone23.minibank.model.User
import com.mlone23.minibank.security.SecurityConfig
import com.mlone23.minibank.service.RegisterService
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import kotlin.test.Test

@WebMvcTest(RegistryController::class)
@Import(SecurityConfig::class)
class ControllerSecurityTest(
) {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var registerService: RegisterService

    @Test
    fun `home endpoints should require authentication`() {
        mockMvc.perform(get("/api/"))
            .andExpect(status().isUnauthorized)
    }


    @Test
    @WithMockUser
    fun `home endpoints should work for authentication user`() {
        mockMvc.perform(get("/api/"))
            .andExpect(status().isOk)
    }


    @Test
    fun `register endpoints should be public`() {
        val user = User("test@gmail.com", "test", "encodePassword")
        `when`(registerService.registryUser("test@gmail.com", "password", "test"))
            .thenReturn(user)

        mockMvc.perform(post("/api/register")
            .param("email", "test@gmail.com")
            .param("password", "password")
            .param("name", "Test User")
        )
            .andExpect(status().isOk)
            .andReturn()
    }
}