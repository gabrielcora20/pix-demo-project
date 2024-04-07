package com.gabrielcora.pix.payment.application.dto

import java.time.LocalDateTime
import java.util.*

data class UpdatePaymentDTO(
    val paymentDate: LocalDateTime?,
    val value: Double,
    val description: String,
    val recurrence: String,
    val pixKey: String,
)