package com.gabrielcora.pix.payment.domain.events.handlers

import com.gabrielcora.pix.payment.domain.events.Event
import com.gabrielcora.pix.payment.domain.events.PaymentRegisteredEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class PaymentRegisteredEventHandler(private val rabbitTemplate: RabbitTemplate) {
    private val routingKey: String = "payment-registered"

    @Async
    @EventListener(classes = [PaymentRegisteredEvent::class])
    fun handlePaymentRegisteredEvent(event: Event) {
        rabbitTemplate.convertAndSend("payment.changes", routingKey, event.payment)
    }
}
