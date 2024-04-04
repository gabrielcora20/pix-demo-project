package com.gabrielcora.cqrspackage.infra.crosscutting.bus.interfaces

import com.gabrielcora.cqrspackage.domain.events.Event

interface EventBus {
    fun send(event: Event)

    fun sendAll(events: List<Event>) {
        events.forEach(this::send)
    }
}
