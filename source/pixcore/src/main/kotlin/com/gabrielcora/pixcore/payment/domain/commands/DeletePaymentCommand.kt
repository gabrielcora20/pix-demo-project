package com.gabrielcora.pixcore.payment.domain.commands

import com.gabrielcora.cqrspackage.domain.commands.Command
import com.gabrielcora.pixcore.payment.domain.commands.results.DeletePaymentCommandResult

data class DeletePaymentCommand(
    val id: String
): Command<DeletePaymentCommandResult>