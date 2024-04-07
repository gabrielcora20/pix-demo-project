package com.gabrielcora.pix.payment.domain.events

import com.gabrielcora.pix.payment.domain.models.Payment
import org.bson.types.ObjectId

data class PaymentDeletedEvent(override val payment: Payment) : Event()