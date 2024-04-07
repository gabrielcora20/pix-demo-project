package com.gabrielcora.pix.payment.application.dto

import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum
import java.time.LocalDateTime

data class PaymentDto(
    val id: String,
    val status: StatusEnum,
    val inclusionDate: LocalDateTime,
    val paymentDate: LocalDateTime?,
    val value: Double,
    val description: String,
    val recurrence: RecurrenceDto?,
    val destination: DestinationDto,
)