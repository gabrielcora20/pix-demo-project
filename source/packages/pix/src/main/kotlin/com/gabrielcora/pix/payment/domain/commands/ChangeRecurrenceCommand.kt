package com.gabrielcora.pix.payment.domain.commands

import com.gabrielcora.pix.payment.domain.commands.results.ChangeRecurrenceCommandResult
import com.gabrielcora.pix.payment.domain.models.Recurrence

data class ChangeRecurrenceCommand(
    val id: String,
    val recurrence: Recurrence
) : Command<ChangeRecurrenceCommandResult>