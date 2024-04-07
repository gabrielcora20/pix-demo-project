package com.gabrielcora.pix.payment.domain.commands.results

import com.gabrielcora.pix.payment.domain.models.Payment

class ChangeRecurrenceCommandResult(val updatedPayment: Payment, val warnings: Iterable<String>? = null)