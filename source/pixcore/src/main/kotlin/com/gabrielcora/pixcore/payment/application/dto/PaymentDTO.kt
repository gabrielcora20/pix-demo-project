package com.gabrielcora.pixcore.payment.application.dto

import com.gabrielcora.pixcore.payment.domain.models.enums.StatusEnum
import java.util.*

data class PaymentDTO(
    val id: String,
    val status: StatusEnum,
    val inclusionDate: Date,
    val paymentDate: Date,
    val value: Double,
    val description: String,
    val recurrence: String,
    val destination: String,
)