package com.gabrielcora.pix.payment.domain.events.handlers

import com.gabrielcora.pix.payment.domain.events.Event
import com.gabrielcora.pix.payment.domain.events.PaymentDeletedEvent
import com.gabrielcora.pix.payment.domain.events.PaymentRegisteredEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class PaymentDeletedEventHandler {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Async
    @EventListener(classes = [PaymentDeletedEvent::class])
    fun handlePaymentDeletedEvent(event: Event) {
        log.info("Handling an event - $event")
    }
}
