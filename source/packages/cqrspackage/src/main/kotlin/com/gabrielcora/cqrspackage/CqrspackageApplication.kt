package com.gabrielcora.cqrspackage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CqrspackageApplication

fun main(args: Array<String>) {
	runApplication<CqrspackageApplication>(*args)
}
