package com.gabrielcora.listener.dto

import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum
import java.time.LocalDateTime

data class PaymentEventResponseDto(
    val id: String,
    val status: StatusEnum,
    val inclusionDate: LocalDateTime,
    val paymentDate: LocalDateTime?,
    val value: Double,
    val description: String,
    val recurrence: RecurrenceEventResponseDto?,
    val destination: DestinationEventResponseDto,
)