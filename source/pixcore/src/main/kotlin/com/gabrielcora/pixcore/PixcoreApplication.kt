package com.gabrielcora.pixcore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.gabrielcora")
class PixcoreApplication

fun main(args: Array<String>) {
    runApplication<PixcoreApplication>(*args)
}
