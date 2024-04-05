package com.gabrielcora.pix.payment.domain.events

import org.bson.types.ObjectId

data class PaymentUpdatedEvent (override val id: ObjectId) : Event()