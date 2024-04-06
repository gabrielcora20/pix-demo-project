package com.gabrielcora.pix.payment.infra.crosscutting.bus.interfaces

import com.gabrielcora.pix.payment.domain.events.Event

interface EventBus {
    fun send(event: Event)
    fun clear()
}
