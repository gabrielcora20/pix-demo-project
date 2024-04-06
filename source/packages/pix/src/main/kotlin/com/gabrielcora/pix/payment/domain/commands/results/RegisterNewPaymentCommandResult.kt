package com.gabrielcora.pix.payment.domain.commands.results

class RegisterNewPaymentCommandResult(val success: Boolean, val warnings: Iterable<String>? = null)