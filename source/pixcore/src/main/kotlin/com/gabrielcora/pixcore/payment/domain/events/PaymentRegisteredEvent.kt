package com.gabrielcora.pixcore.payment.domain.events

import org.bson.types.ObjectId

data class PaymentRegisteredEvent (override val id: ObjectId) : com.gabrielcora.cqrspackage.domain.events.Event()