package com.mlone23.minibank

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MinibankApplication

fun main(args: Array<String>) {
	runApplication<MinibankApplication>(*args)

	println("Misha")

	println("Misha test 2")

}
