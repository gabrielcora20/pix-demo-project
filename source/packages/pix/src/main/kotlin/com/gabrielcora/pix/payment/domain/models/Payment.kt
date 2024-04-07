package com.gabrielcora.pix.payment.domain.models

import com.gabrielcora.pix.payment.domain.models.enums.FrequencyTypeEnum
import com.gabrielcora.pix.payment.domain.models.enums.PixKeyTypeEnum
import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("payment")
class Payment(
    var paymentDate: LocalDateTime? = LocalDateTime.now(),
    var value: Double = 0.0,
    var description: String = "",
    var recurrence: Recurrence? = null,
    var destination: Destination? = null,
    var status: StatusEnum = StatusEnum.EFFECTED,
    var inclusionDate: LocalDateTime = LocalDateTime.now(),
) : DomainEntity() {
    fun getStatusByPaymentDate(currentDate: LocalDateTime): StatusEnum {
        val tomorrow = currentDate.plusDays(1)

        if (paymentDate!!.isAfter(tomorrow))
            return StatusEnum.SCHEDULED

        return StatusEnum.EFFECTED
    }
}

data class Destination(
    val pixKey: String,
    val keyType: PixKeyTypeEnum,
)

data class Recurrence constructor(
    val endDate: LocalDateTime,
    val frequencyType: FrequencyTypeEnum,
)