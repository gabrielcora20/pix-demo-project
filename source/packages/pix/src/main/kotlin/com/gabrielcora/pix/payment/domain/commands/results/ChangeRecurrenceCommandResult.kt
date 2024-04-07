package com.gabrielcora.pix.payment.domain.commands.results

class ChangeRecurrenceCommandResult(val success: Boolean, val warnings: Iterable<String>? = null)