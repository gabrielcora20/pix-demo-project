package com.gabrielcora.pix.payment.application.dto

import java.time.LocalDateTime

data class UpdatePaymentDTO(
    val paymentDate: LocalDateTime?,
    val value: Double,
    val description: String,
    val recurrence: RecurrenceDTO?,
    val pixKey: String,
)