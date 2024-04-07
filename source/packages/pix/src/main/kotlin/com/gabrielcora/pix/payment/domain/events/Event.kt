package com.gabrielcora.pix.payment.domain.events

import com.gabrielcora.pix.payment.domain.models.Payment

abstract class Event {
    abstract val payment: Payment
}