package com.gabrielcora.pixcore.payment.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@Schema(description = "Informações do pagamento a ser cadastrado.")
data class RegisterPaymentDTO(
    @field:Schema(
        description = "O status do pagamento",
        example = "1",
        type = "int",
        minimum = "0",
        maximum = "2"
    )
    val status: Int,
    val dataInclusao: Date,
    val dataPagamento: Date,
    val valorPagamento: Double,
    val descricaoPagamento: String,
    val dadosRecorrencia: String,
    val destinoPagamento: String,
)