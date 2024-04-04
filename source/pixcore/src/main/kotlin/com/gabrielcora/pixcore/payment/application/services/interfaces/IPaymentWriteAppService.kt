package com.gabrielcora.pixcore.payment.application.services.interfaces

import com.gabrielcora.pixcore.payment.application.dto.RegisterPaymentDTO
import com.gabrielcora.pixcore.payment.application.dto.UpdatePaymentDTO
import com.gabrielcora.pixcore.payment.application.dto.PartiallyUpdatePaymentDTO
import com.gabrielcora.pixcore.payment.domain.commands.results.PartiallyUpdatePaymentCommandResult
import com.gabrielcora.pixcore.payment.domain.commands.results.RegisterNewPaymentCommandResult
import com.gabrielcora.pixcore.payment.domain.commands.results.UpdatePaymentCommandResult

interface IPaymentWriteAppService {
    suspend fun register(payment: RegisterPaymentDTO): RegisterNewPaymentCommandResult
    suspend fun update(id: String, payment: UpdatePaymentDTO): UpdatePaymentCommandResult
    suspend fun partiallyUpdate(id: String, payment: PartiallyUpdatePaymentDTO): PartiallyUpdatePaymentCommandResult
}