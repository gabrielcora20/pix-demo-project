package com.gabrielcora.pix.payment.domain.events.handlers

import com.gabrielcora.pix.payment.domain.events.Event
import com.gabrielcora.pix.payment.domain.events.PaymentUpdatedEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class PaymentUpdatedEventHandler(private val rabbitTemplate: RabbitTemplate) {
    private val routingKey: String = "payment-updated"

    @Async
    @EventListener(classes = [PaymentUpdatedEvent::class])
    fun handlePaymentUpdatedEvent(event: PaymentUpdatedEvent) {
        rabbitTemplate.convertAndSend("payment.changes", routingKey, event.payment)
    }
}
