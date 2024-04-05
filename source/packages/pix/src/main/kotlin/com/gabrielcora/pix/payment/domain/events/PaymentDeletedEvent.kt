package com.gabrielcora.pix.payment.domain.events

import org.bson.types.ObjectId

data class PaymentDeletedEvent(override val id: ObjectId) : Event()