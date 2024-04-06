package com.gabrielcora.pix.payment.domain.models

import com.gabrielcora.pix.payment.domain.events.PaymentDeletedEvent
import com.gabrielcora.pix.payment.domain.events.PaymentPatchedEvent
import com.gabrielcora.pix.payment.domain.events.PaymentRegisteredEvent
import com.gabrielcora.pix.payment.domain.events.PaymentUpdatedEvent
import com.gabrielcora.pix.payment.domain.models.anotations.ShouldNotBePatched
import com.gabrielcora.pix.payment.domain.models.enums.PixKeyTypeEnum
import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("payment")
class Payment(
    var paymentDate: Date? = Date(),
    var value: Double = 0.0,
    var description: String = "",
    var recurrence: String = "",
    var destination: Destination? = null,
    @ShouldNotBePatched var status: StatusEnum = StatusEnum.EFFECTED,
    @ShouldNotBePatched var inclusionDate: Date = Date(),
) : DomainEntity() {
    fun getStatusByPaymentDate(currentDate: Date): StatusEnum {
        if (paymentDate!!.after(currentDate))
            return StatusEnum.SCHEDULED

        return StatusEnum.EFFECTED
    }
}

data class Destination(
    val pixKey: String,
    val keyType: PixKeyTypeEnum,
)