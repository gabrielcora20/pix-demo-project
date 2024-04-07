package com.gabrielcora.pix.payment.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid

@Schema(description = "Informações do pagamento a ser cadastrado.")
data class ChangeRecurrenceDto(
    val recurrence: @Valid RecurrenceDto?
)