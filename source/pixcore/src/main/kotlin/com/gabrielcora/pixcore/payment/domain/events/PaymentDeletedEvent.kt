package com.gabrielcora.pixcore.payment.domain.events

import com.gabrielcora.cqrspackage.domain.events.Event
import org.bson.types.ObjectId

data class PaymentDeletedEvent(override val id: ObjectId) : Event()