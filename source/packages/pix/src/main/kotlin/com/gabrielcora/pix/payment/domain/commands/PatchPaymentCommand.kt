package com.gabrielcora.pix.payment.domain.commands

import com.gabrielcora.pix.payment.application.dto.PatchPaymentDTO
import com.gabrielcora.pix.payment.domain.commands.results.PatchPaymentCommandResult

data class PatchPaymentCommand(
    val id: String,
    val payment: PatchPaymentDTO,
) : Command<PatchPaymentCommandResult>