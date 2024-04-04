package com.gabrielcora.pixcore.payment.infra.crosscutting.bus.notations

import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@EventListener
@Async
annotation class PaymentEventListener
