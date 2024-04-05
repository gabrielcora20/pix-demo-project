package com.gabrielcora.pixcore.payment.domain.commands

import com.gabrielcora.cqrspackage.domain.commands.Command
import com.gabrielcora.pixcore.payment.domain.commands.results.UpdatePaymentCommandResult
import java.util.*

data class UpdatePaymentCommand(
    val id: String,
    val paymentDate: Date,
    val value: Double,
    val description: String,
    val recurrence: String,
    val destination: String,
): Command<UpdatePaymentCommandResult>