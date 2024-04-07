package com.gabrielcora.listener

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.gabrielcora")
class ListenerApplication

fun main(args: Array<String>) {
	runApplication<ListenerApplication>(*args)
}
