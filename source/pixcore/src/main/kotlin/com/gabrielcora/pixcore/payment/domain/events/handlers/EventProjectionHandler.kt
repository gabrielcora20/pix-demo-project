package com.gabrielcora.pixcore.payment.domain.events.handlers

import com.gabrielcora.cqrspackage.domain.events.Event
import com.gabrielcora.pixcore.payment.infra.crosscutting.bus.notations.PaymentEventListener
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class EventProjectionHandler {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @PaymentEventListener
    fun handleProductEvents(event: com.gabrielcora.cqrspackage.domain.events.Event) {
        log.info("Handling an event - $event")
    }
}
