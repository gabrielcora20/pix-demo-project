package com.gabrielcora.cqrspackage.infra.crosscutting.bus

import com.gabrielcora.cqrspackage.domain.events.Event
import com.gabrielcora.cqrspackage.infra.crosscutting.bus.interfaces.EventBus
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class SpringEventBus(private val publisher: ApplicationEventPublisher) : EventBus {
    override fun send(event: Event) {
        publisher.publishEvent(event)
    }
}