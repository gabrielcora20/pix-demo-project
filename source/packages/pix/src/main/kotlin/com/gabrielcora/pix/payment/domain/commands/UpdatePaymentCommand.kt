package com.gabrielcora.pix.payment.domain.commands

import com.gabrielcora.pix.payment.domain.commands.results.UpdatePaymentCommandResult
import com.gabrielcora.pix.payment.domain.models.Recurrence
import java.time.LocalDateTime
import java.util.*

data class UpdatePaymentCommand(
    val id: String,
    val paymentDate: LocalDateTime?,
    val value: Double,
    val description: String,
    val recurrence: Recurrence?,
    val pixKey: String,
) : Command<UpdatePaymentCommandResult>