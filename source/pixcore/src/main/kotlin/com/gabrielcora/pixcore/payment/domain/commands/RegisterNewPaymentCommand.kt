package com.gabrielcora.pixcore.payment.domain.commands

import com.gabrielcora.pixcore.payment.domain.commands.results.RegisterNewPaymentCommandResult
import java.util.*

data class RegisterNewPaymentCommand(
    val status: Int,
    val dataInclusao: Date,
    val dataPagamento: Date,
    val valorPagamento: Double,
    val descricaoPagamento: String,
    val dadosRecorrencia: String,
    val destinoPagamento: String,
): Command<RegisterNewPaymentCommandResult>