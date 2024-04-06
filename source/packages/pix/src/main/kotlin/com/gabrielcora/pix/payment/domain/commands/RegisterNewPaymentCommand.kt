package com.gabrielcora.pix.payment.domain.commands

import com.gabrielcora.pix.payment.domain.commands.results.RegisterNewPaymentCommandResult
import java.util.*

data class RegisterNewPaymentCommand(
    val paymentDate: Date?,
    val value: Double,
    val description: String,
    val recurrence: String,
    val destination: String,
): Command<RegisterNewPaymentCommandResult>