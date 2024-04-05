package com.gabrielcora.pix.payment.infra.crosscutting.bus.anotations

import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@EventListener
@Async
annotation class PaymentEventListener
