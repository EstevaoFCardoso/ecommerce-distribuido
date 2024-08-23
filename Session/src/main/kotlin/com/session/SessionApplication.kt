package com.session

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition(info = (Info(title = "Swagger Api Documentation", version = "1",
	description = "Aplicação faz o gerenciamento de filmes e seus horários")))
class SessionApplication

fun main(args: Array<String>) {
	runApplication<SessionApplication>(*args)
}
