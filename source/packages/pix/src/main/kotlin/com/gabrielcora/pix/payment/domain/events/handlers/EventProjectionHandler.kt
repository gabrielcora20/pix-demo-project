package com.gabrielcora.pix.payment.domain.events.handlers

import com.gabrielcora.pix.payment.domain.events.Event
import com.gabrielcora.pix.payment.infra.crosscutting.bus.anotations.PaymentEventListener
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class EventProjectionHandler {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @PaymentEventListener
    fun handleProductEvents(event: Event) {
        log.info("Handling an event - $event")
    }
}
