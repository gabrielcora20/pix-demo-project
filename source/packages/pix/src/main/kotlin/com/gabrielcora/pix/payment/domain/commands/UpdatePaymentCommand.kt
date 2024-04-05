package com.gabrielcora.pix.payment.domain.commands

import com.gabrielcora.pix.payment.domain.commands.results.UpdatePaymentCommandResult
import java.util.*

data class UpdatePaymentCommand(
    val id: String,
    val paymentDate: Date,
    val value: Double,
    val description: String,
    val recurrence: String,
    val destination: String,
): Command<UpdatePaymentCommandResult>