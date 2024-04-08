package com.gabrielcora.pix.payment.application.dto

import com.gabrielcora.pix.payment.domain.models.enums.FrequencyTypeEnum
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

@Schema(description = "Informações da recorrência do pagamento.")
data class RecurrenceDto(
    @field:Schema(
        description = "A data de término da recorrência",
        defaultValue = "2025-04-30T23:59:59",
        type = "date-time",
        required = true
    )
    @field:NotNull(message = "A data de término da recorrência não pode ser nula")
    val endDate: LocalDateTime?,

    @field:Schema(
        description = "O tipo de frequência da recorrência",
        defaultValue = "MONTHLY",
        required = true
    )
    @field:NotBlank(message = "A frequencia da recorrência não pode estar vazia")
    @field:NotNull(message = "A frequencia da recorrência não pode ser nula")
    val frequencyType: FrequencyTypeEnum?,
)