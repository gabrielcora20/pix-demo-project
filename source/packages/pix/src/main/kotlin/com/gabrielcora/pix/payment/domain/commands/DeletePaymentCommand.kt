package com.gabrielcora.pix.payment.domain.commands

import com.gabrielcora.pix.payment.domain.commands.results.DeletePaymentCommandResult

data class DeletePaymentCommand(
    val id: String,
) : Command<DeletePaymentCommandResult>