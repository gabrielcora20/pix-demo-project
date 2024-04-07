package com.gabrielcora.pix.payment.domain.models

import com.gabrielcora.pix.payment.domain.models.enums.FrequencyTypeEnum
import com.gabrielcora.pix.payment.domain.models.enums.PixKeyTypeEnum
import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum
import jakarta.validation.constraints.NotNull
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("payment")
class Payment(
    @NotNull
    var paymentDate: LocalDateTime? = LocalDateTime.now(),
    @NotNull
    var value: Double = 0.0,
    @NotNull
    var description: String = "",
    var recurrence: Recurrence? = null,
    var destination: Destination? = null,
    @NotNull
    var status: StatusEnum = StatusEnum.EFFECTED,
    @NotNull
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
    @NotNull val pixKey: String,
    @NotNull val keyType: PixKeyTypeEnum,
)

data class Recurrence constructor(
    @NotNull val endDate: LocalDateTime,
    @NotNull val frequencyType: FrequencyTypeEnum,
)