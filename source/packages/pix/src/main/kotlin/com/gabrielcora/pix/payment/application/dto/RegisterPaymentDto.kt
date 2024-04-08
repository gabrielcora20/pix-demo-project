package com.gabrielcora.pix.payment.application.dto

//import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiModelProperty
//import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import jakarta.validation.constraints.FutureOrPresent
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.time.LocalDateTime

@Schema(description = "Informações do pagamento a ser cadastrado.")
data class RegisterPaymentDto(
    @field:ApiModelProperty(
        value = "A data do pagamento",
        example = "2024-04-05T13:57:42.967Z",
        dataType = "date-time",
        required = true
    )
    @field:FutureOrPresent(message = "A data do pagamento não deve estar no passado")
    val paymentDate: LocalDateTime?,

    @field:ApiModelProperty(
        value = "O valor do pagamento",
        example = "100.00",
        required = true
    )
    @field:Positive(message = "O valor do pagamento precisa ser maior que 0")
    @field:NotNull(message = "O valor do pagamento não pode ser nulo")
    val value: Double?,

    @field:ApiModelProperty(
        value = "A descrição do pagamento",
        example = "Descrição de pagamento",
        required = true
    )
    @field:NotBlank(message = "A descrição do pagamento não pode estar vazia")
    @field:NotNull(message = "A descrição do pagamento não pode ser nula")
    val description: String?,

    val recurrence: @Valid RecurrenceDto?,

    @field:ApiModelProperty(
        value = "A chave pix",
        example = "12345678901",
        required = true
    )
    @field:NotBlank(message = "A chave pix não pode estar vazia")
    @field:NotNull(message = "A chave pix não pode ser nula")
    val pixKey: String?,
)