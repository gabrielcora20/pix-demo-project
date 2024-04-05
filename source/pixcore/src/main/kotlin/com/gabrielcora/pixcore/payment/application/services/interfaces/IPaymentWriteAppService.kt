package com.gabrielcora.pixcore.payment.application.services.interfaces

import com.gabrielcora.pixcore.payment.application.dto.RegisterPaymentDTO
import com.gabrielcora.pixcore.payment.application.dto.UpdatePaymentDTO
import com.gabrielcora.pixcore.payment.domain.commands.results.PatchPaymentCommandResult
import com.gabrielcora.pixcore.payment.domain.commands.results.RegisterNewPaymentCommandResult
import com.gabrielcora.pixcore.payment.domain.commands.results.UpdatePaymentCommandResult

interface IPaymentWriteAppService {
    suspend fun register(payment: RegisterPaymentDTO): RegisterNewPaymentCommandResult
    suspend fun update(id: String, payment: UpdatePaymentDTO): UpdatePaymentCommandResult
    suspend fun patch(id: String, payment: Map<String, Any>): PatchPaymentCommandResult
}