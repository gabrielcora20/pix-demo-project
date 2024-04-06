package com.gabrielcora.pix.payment.application.dto

import java.util.*

data class UpdatePaymentDTO(
    val paymentDate: Date?,
    val value: Double,
    val description: String,
    val recurrence: String,
    val pixKey: String,
)