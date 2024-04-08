package com.gabrielcora.pix.payment.domain.commands.results

import com.gabrielcora.pix.payment.domain.models.Payment

class DeletePaymentCommandResult(val deletedPayment: Payment)