package com.gabrielcora.pix.payment.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import jakarta.validation.constraints.FutureOrPresent
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.time.LocalDateTime

@Schema(description = "Informações do pagamento a ser atualizado.")
data class UpdatePaymentDto(
    @field:Schema(
        description = "A data do pagamento",
        defaultValue = "2025-04-05T13:57:42.967Z",
        type = "date-time",
        required = false
    )
    @field:FutureOrPresent(message = "A data do pagamento não deve estar no passado")
    val paymentDate: LocalDateTime?,

    @field:Schema(
        description = "O valor do pagamento",
        defaultValue = "100.00",
        required = false
    )
    @field:Positive(message = "O valor do pagamento precisa ser maior que 0")
    @field:NotNull(message = "O valor do pagamento não pode ser nulo")
    val value: Double?,

    @field:Schema(
        description = "A descrição do pagamento",
        defaultValue = "Pagamento de serviço",
        required = false
    )
    @field:NotBlank(message = "A descrição do pagamento não pode estar vazia")
    @field:NotNull(message = "A descrição do pagamento não pode ser nula")
    val description: String?,

    @field:Schema(
        description = "As informações da recorrência do pagamento.",
        nullable = true
    )
    val recurrence: @Valid RecurrenceDto?,

    @field:Schema(
        description = "A chave pix",
        defaultValue = "12345678901",
        required = false
    )
    @field:NotBlank(message = "A chave pix não pode estar vazia")
    @field:NotNull(message = "A chave pix não pode ser nula")
    val pixKey: String?,
)