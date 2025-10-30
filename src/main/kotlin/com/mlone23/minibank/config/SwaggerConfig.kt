package com.mlone23.minibank.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
open class SwaggerConfig {

    @Bean
    open fun openApi(): OpenAPI = OpenAPI()
        .servers(
            listOf(
                Server().url("http://localhost:8080").description("Local Server")
            )
        )
        .info(
            Info()
                .title("Investment App API")
                .description("API for investment portfolio management")
                .version("1.0")
        )
        .addSecurityItem(SecurityRequirement().addList("BasicAuth"))
        .components(
            io.swagger.v3.oas.models.Components()
                .addSecuritySchemes(
                    "BasicAuth",
                    SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("basic")
                )
        )
}