package com.gabrielcora.pix.payment.domain.models

import com.gabrielcora.pix.payment.domain.events.PaymentDeletedEvent
import com.gabrielcora.pix.payment.domain.events.PaymentPatchedEvent
import com.gabrielcora.pix.payment.domain.events.PaymentRegisteredEvent
import com.gabrielcora.pix.payment.domain.events.PaymentUpdatedEvent
import com.gabrielcora.pix.payment.domain.models.anotations.ShouldNotBePatched
import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum
import java.util.*
import org.springframework.data.mongodb.core.mapping.Document

@Document("payment")
class Payment(
    var paymentDate: Date? = Date(),
    var value: Double = 0.0,
    var description: String = "",
    var recurrence: String = "",
    var destination: String = "",
    @ShouldNotBePatched var status: StatusEnum = StatusEnum.EFFECTED,
    @ShouldNotBePatched var inclusionDate: Date = Date(),
) : DomainEntity() {
    fun raise(event: PaymentRegisteredEvent) {
        raise(event)
    }
    fun raise(event: PaymentUpdatedEvent) {
        raise(event)
    }
    fun raise(event: PaymentPatchedEvent) {
        raise(event)
    }
    fun raise(event: PaymentDeletedEvent) {
        raise(event)
    }
}