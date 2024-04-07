package com.gabrielcora.pix.payment.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Positive
import java.time.LocalDateTime

@Schema(description = "Informações do pagamento a ser cadastrado.")
data class ChangeRecurrenceDTO(
    val recurrence: RecurrenceDTO
)