package com.gabrielcora.cqrspackage.domain.events

import org.bson.types.ObjectId

abstract class Event() {
    abstract val id: ObjectId

    fun getDomainEntityId(): String{
        return id.toString()
    }
}