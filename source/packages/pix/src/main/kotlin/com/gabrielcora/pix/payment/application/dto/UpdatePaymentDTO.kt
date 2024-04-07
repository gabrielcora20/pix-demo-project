package com.gabrielcora.pix.payment.application.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.FutureOrPresent
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.time.LocalDateTime

data class UpdatePaymentDTO(
    @field:FutureOrPresent(message = "A data do pagamento não deve estar no passado")
    val paymentDate: LocalDateTime?,

    @field:Positive(message = "O valor do pagamento precisa ser maior que 0")
    @field:NotNull(message = "O valor do pagamento não pode ser nulo")
    val value: Double?,

    @field:NotBlank(message = "A descrição do pagamento não pode estar vazia")
    @field:NotNull(message = "A descrição do pagamento não pode ser nula")
    val description: String?,

    val recurrence: @Valid RecurrenceDTO?,

    @field:NotBlank(message = "A chave pix não pode estar vazia")
    @field:NotNull(message = "A chave pix não pode ser nula")
    val pixKey: String?,
)