package com.gabrielcora.pix.payment.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid

@Schema(description = "Informações para alteração da recorrência do pagamento.")
data class ChangeRecurrenceDto(
    @field:Schema(
        description = "As informações da nova recorrência do pagamento.",
        nullable = true
    )
    val recurrence: @Valid RecurrenceDto?
)