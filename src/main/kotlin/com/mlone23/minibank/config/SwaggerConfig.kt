package com.mlone23.minibank.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {

    @Bean
    fun openApi(): OpenAPI = OpenAPI()
        .servers(
            listOf(
                Server().url("http://localhost:8080")
            )
        )
        .info(
            Info()
                .title("Mini-Bank")
                .description("API for bank")
                .version("1.0")
        )
        .components(
            Components()
                .addSecuritySchemes(
                    "basicAuth",
                    SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("basic")
                )
        )
        .addSecurityItem(
            SecurityRequirement().addList("bearerAuth"))
}