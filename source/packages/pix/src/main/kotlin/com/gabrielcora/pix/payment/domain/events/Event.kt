package com.gabrielcora.pix.payment.domain.events

import org.bson.types.ObjectId

abstract class Event() {
    abstract val id: ObjectId

    fun getDomainEntityId(): String{
        return id.toString()
    }
}