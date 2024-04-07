package com.gabrielcora.pix.payment.application.services.interfaces

import com.gabrielcora.pix.payment.application.dto.ChangeRecurrenceDto
import com.gabrielcora.pix.payment.application.dto.RegisterPaymentDto
import com.gabrielcora.pix.payment.application.dto.UpdatePaymentDto
import com.gabrielcora.pix.payment.domain.commands.results.DeletePaymentCommandResult
import com.gabrielcora.pix.payment.domain.commands.results.ChangeRecurrenceCommandResult
import com.gabrielcora.pix.payment.domain.commands.results.RegisterNewPaymentCommandResult
import com.gabrielcora.pix.payment.domain.commands.results.UpdatePaymentCommandResult

interface IPaymentWriteAppService {
    suspend fun register(payment: RegisterPaymentDto): RegisterNewPaymentCommandResult
    suspend fun update(id: String, payment: UpdatePaymentDto): UpdatePaymentCommandResult
    suspend fun changeRecurrence(id: String, paymentRecurrence: ChangeRecurrenceDto): ChangeRecurrenceCommandResult
    suspend fun delete(id: String): DeletePaymentCommandResult
}