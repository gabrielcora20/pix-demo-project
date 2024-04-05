package com.gabrielcora.pixcore.payment.application.dto

import java.util.*

data class UpdatePaymentDTO(
    val paymentDate: Date,
    val value: Double,
    val description: String,
    val recurrence: String,
    val destination: String,
)