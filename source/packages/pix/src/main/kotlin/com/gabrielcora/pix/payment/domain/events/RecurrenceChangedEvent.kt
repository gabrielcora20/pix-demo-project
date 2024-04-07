package com.gabrielcora.pix.payment.domain.events

import org.bson.types.ObjectId

data class RecurrenceChangedEvent(override val id: ObjectId) : Event()