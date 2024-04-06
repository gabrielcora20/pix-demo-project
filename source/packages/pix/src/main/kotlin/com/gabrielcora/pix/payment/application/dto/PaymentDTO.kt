package com.gabrielcora.pix.payment.application.dto

import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum
import java.util.*

data class PaymentDTO(
    val id: String,
    val status: StatusEnum,
    val inclusionDate: Date,
    val paymentDate: Date?,
    val value: Double,
    val description: String,
    val recurrence: String,
    val destination: String,
)