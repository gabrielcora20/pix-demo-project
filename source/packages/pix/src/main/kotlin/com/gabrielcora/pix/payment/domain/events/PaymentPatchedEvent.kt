package com.gabrielcora.pix.payment.domain.events

import org.bson.types.ObjectId

data class PaymentPatchedEvent(override val id: ObjectId) : Event()