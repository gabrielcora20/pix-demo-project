package com.gabrielcora.presentation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.gabrielcora")
class PresentationApplication

fun main(args: Array<String>) {
    runApplication<PresentationApplication>(*args)
}
