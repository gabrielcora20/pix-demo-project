package com.gabrielcora.listener.dto

import java.time.LocalDateTime

data class PaymentEventResponseDto(
    val id: String,
    val status: String,
    val inclusionDate: LocalDateTime,
    val paymentDate: LocalDateTime?,
    val value: Double,
    val description: String,
    val recurrence: RecurrenceEventResponseDto?,
    val destination: DestinationEventResponseDto,
)