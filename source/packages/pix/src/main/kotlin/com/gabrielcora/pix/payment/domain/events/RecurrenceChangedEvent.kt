package com.gabrielcora.pix.payment.domain.events

import com.gabrielcora.pix.payment.domain.models.Payment

data class RecurrenceChangedEvent(override val payment: Payment) : Event()