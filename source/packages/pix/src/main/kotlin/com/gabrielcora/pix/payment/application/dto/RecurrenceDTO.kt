package com.gabrielcora.pix.payment.application.dto

import com.gabrielcora.pix.payment.domain.models.enums.FrequencyTypeEnum
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class RecurrenceDTO(
    @field:NotNull(message = "A data de término da recorrência não pode ser nula")
    val endDate: LocalDateTime?,

    @field:NotBlank(message = "A frequencia da recorrência não pode estar vazia")
    @field:NotNull(message = "A frequencia da recorrência não pode ser nula")
    val frequencyType: FrequencyTypeEnum?,
)