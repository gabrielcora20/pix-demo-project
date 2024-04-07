package com.gabrielcora.pix.payment.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Positive
import java.time.LocalDateTime

@Schema(description = "Informações do pagamento a ser cadastrado.")
data class RegisterPaymentDTO(
    @field:Schema(
        description = "A data do pagamento",
        example = "2024-04-05T13:57:42.967Z",
        type = "date",
    )
    val paymentDate: LocalDateTime?,
    @field:Positive(message = "O valor do pagamento não pode ser nulo")
    val value: Double,
    val description: String,
    val recurrence: RecurrenceDTO?,
    val pixKey: String,
)