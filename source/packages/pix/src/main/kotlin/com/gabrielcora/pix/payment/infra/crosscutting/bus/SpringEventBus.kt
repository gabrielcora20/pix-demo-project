package com.gabrielcora.pix.payment.infra.crosscutting.bus

import com.gabrielcora.pix.payment.domain.events.Event
import com.gabrielcora.pix.payment.infra.crosscutting.bus.interfaces.EventBus
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.ApplicationEventMulticaster
import org.springframework.context.event.SimpleApplicationEventMulticaster
import org.springframework.stereotype.Component

@Component
class SpringEventBus(
    private val publisher: ApplicationEventPublisher,
    private val eventMulticaster: ApplicationEventMulticaster,
) : EventBus {
    override fun send(event: Event) {
        publisher.publishEvent(event)
    }

    override fun clear() {
        if (publisher is SimpleApplicationEventMulticaster) {
            publisher.removeAllListeners()
        }
    }
}