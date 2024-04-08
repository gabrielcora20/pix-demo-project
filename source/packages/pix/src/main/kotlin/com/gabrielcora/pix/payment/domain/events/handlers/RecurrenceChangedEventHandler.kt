package com.gabrielcora.pix.payment.domain.events.handlers

import com.gabrielcora.pix.payment.domain.events.Event
import com.gabrielcora.pix.payment.domain.events.PaymentUpdatedEvent
import com.gabrielcora.pix.payment.domain.events.RecurrenceChangedEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class RecurrenceChangedEventHandler(private val rabbitTemplate: RabbitTemplate) {
    private val routingKey: String = "payment-recurrence-updated"

    @Async
    @EventListener(classes = [RecurrenceChangedEvent::class])
    fun handleRecurrenceChangedEvent(event: RecurrenceChangedEvent) {
        rabbitTemplate.convertAndSend("payment.changes", routingKey, event.payment)
    }
}
