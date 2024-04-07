package com.gabrielcora.listener.dto

import com.gabrielcora.pix.payment.domain.models.enums.FrequencyTypeEnum
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class RecurrenceEventResponseDto(
    val endDate: LocalDateTime?,
    val frequencyType: FrequencyTypeEnum?,
)