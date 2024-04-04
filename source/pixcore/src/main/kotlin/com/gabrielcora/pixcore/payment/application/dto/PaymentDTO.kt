package com.gabrielcora.pixcore.payment.application.dto

import java.util.*

data class PaymentDTO(
    val id: String,
    val status: Int,
    val dataInclusao: Date,
    val dataPagamento: Date,
    val valorPagamento: Double,
    val descricaoPagamento: String,
    val dadosRecorrencia: String,
    val destinoPagamento: String,
)