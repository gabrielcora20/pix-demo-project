package com.gabrielcora.pix.payment.domain.events

import org.bson.types.ObjectId

data class PaymentRegisteredEvent (override val id: ObjectId) : Event()