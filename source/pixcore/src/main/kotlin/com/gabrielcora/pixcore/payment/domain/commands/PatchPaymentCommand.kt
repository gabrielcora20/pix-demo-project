package com.gabrielcora.pixcore.payment.domain.commands

import com.gabrielcora.cqrspackage.domain.commands.Command
import com.gabrielcora.pixcore.payment.application.dto.PatchPaymentDTO
import com.gabrielcora.pixcore.payment.domain.commands.results.PatchPaymentCommandResult

data class PatchPaymentCommand(
    val id: String,
    val payment: PatchPaymentDTO,
): Command<PatchPaymentCommandResult>