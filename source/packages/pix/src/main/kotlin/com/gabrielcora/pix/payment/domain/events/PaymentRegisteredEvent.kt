package com.gabrielcora.pix.payment.domain.events

import com.gabrielcora.pix.payment.domain.models.Payment

data class PaymentRegisteredEvent(override val payment: Payment) : Event()