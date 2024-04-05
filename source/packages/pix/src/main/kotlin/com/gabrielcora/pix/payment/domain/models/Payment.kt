package com.gabrielcora.pix.payment.domain.models

import com.gabrielcora.pix.payment.domain.commands.DeletePaymentCommand
import com.gabrielcora.pix.payment.domain.commands.PatchPaymentCommand
import com.gabrielcora.pix.payment.domain.commands.RegisterNewPaymentCommand
import com.gabrielcora.pix.payment.domain.commands.UpdatePaymentCommand
import com.gabrielcora.pix.payment.domain.events.PaymentDeletedEvent
import com.gabrielcora.pix.payment.domain.events.PaymentPatchedEvent
import com.gabrielcora.pix.payment.domain.events.PaymentRegisteredEvent
import com.gabrielcora.pix.payment.domain.events.PaymentUpdatedEvent
import com.gabrielcora.pix.payment.domain.models.anotations.ShouldNotBePatched
import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum
import java.util.*
import org.bson.types.ObjectId
import org.springframework.beans.BeanUtils
import org.springframework.data.mongodb.core.mapping.Document

@Document("payment")
class Payment(
    var paymentDate: Date = Date(),
    var value: Double = 0.0,
    var description: String = "",
    var recurrence: String = "",
    var destination: String = "",
    @ShouldNotBePatched var status: StatusEnum = StatusEnum.EFFECTED,
    @ShouldNotBePatched var inclusionDate: Date = Date(),
) : DomainEntity() {

    fun handle(command: RegisterNewPaymentCommand): ObjectId {
        id = ObjectId()
        status = StatusEnum.EFFECTED
        inclusionDate = Date()
        paymentDate = command.paymentDate
        value = command.value
        description = command.description
        recurrence = command.recurrence
        destination = command.destination

        raise(PaymentRegisteredEvent(id!!))
        return id!!
    }

    fun handle(command: UpdatePaymentCommand): ObjectId {
        id = ObjectId(command.id)
        status = StatusEnum.EFFECTED
        paymentDate = command.paymentDate
        value = command.value
        description = command.description
        recurrence = command.recurrence
        destination = command.destination

        raise(PaymentUpdatedEvent(id!!))
        return id!!
    }

    fun handle(command: PatchPaymentCommand): ObjectId {
        for ((key, value) in command.payment) {
            if (BeanUtils.getPropertyDescriptor(this::class.java, key) != null) {
                try {
                    val field = this::class.java.getDeclaredField(key)
                    field.isAccessible = true

                    if(field.getAnnotation(ShouldNotBePatched::class.java) == null)
                        field.set(this, value)
                } catch (e: NoSuchFieldException) {}
            }
        }

        raise(PaymentPatchedEvent(id!!))
        return id!!
    }

    fun handle(command: DeletePaymentCommand) {
        status = StatusEnum.CANCELED

        raise(PaymentDeletedEvent(id!!))
    }
}