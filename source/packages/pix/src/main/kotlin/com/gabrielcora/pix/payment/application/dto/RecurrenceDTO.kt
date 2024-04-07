package com.gabrielcora.pix.payment.application.dto

import com.gabrielcora.pix.payment.domain.models.enums.FrequencyTypeEnum
import java.time.LocalDateTime

data class RecurrenceDTO(
    val endDate: LocalDateTime,
    val frequencyType: FrequencyTypeEnum,
)