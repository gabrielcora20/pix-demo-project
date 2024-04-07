package com.gabrielcora.pix.payment.application.dto

import com.gabrielcora.pix.payment.domain.models.enums.FrequencyTypeEnum
import com.gabrielcora.pix.payment.domain.models.enums.PixKeyTypeEnum
import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum
import java.time.LocalDateTime
import java.util.*

data class PaymentDTO(
    val id: String,
    val status: StatusEnum,
    val inclusionDate: LocalDateTime,
    val paymentDate: LocalDateTime?,
    val value: Double,
    val description: String,
    val recurrence: String,
    val destination: DestinationDTO,
)