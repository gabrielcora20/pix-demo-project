package com.gabrielcora.pix.payment.domain.models

import com.gabrielcora.pix.payment.domain.events.Event
import com.gabrielcora.pix.payment.domain.models.anotations.ShouldNotBePatched
import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient

abstract class DomainEntity {

    @Id
    @ShouldNotBePatched
    var id: ObjectId? = null

    @Transient
    private val occurredEvents: MutableList<Event> = mutableListOf()

    fun occurredEvents(): List<Event> {
        val events = this.occurredEvents.toMutableList()
        this.occurredEvents.clear()
        return events
    }

    fun raise(event: Event) {
        occurredEvents.add(event)
    }

    override fun equals(other: Any?): Boolean {
        if (other!!.javaClass != this.javaClass) {
            return false
        }
        return EqualsBuilder()
            .append(this.id, (other as DomainEntity).id)
            .isEquals
    }

    override fun hashCode(): Int {
        return HashCodeBuilder()
            .append(id)
            .toHashCode()
    }

    fun getId(): String {
        return id.toString()
    }
}