package com.gabrielcora.pixcore.payment.domain.commands

import com.gabrielcora.cqrspackage.domain.commands.Command
import com.gabrielcora.pixcore.payment.domain.commands.results.PartiallyUpdatePaymentCommandResult
import java.util.*

data class PartiallyUpdatePaymentCommand(
    val id: String,
    val status: Int,
    val dataInclusao: Date,
    val dataPagamento: Date,
    val valorPagamento: Double,
    val descricaoPagamento: String,
    val dadosRecorrencia: String,
    val destinoPagamento: String,
): Command<PartiallyUpdatePaymentCommandResult>