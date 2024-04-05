package com.gabrielcora.pix.payment.application.services.interfaces

import com.gabrielcora.pix.payment.application.dto.PatchPaymentDTO
import com.gabrielcora.pix.payment.application.dto.RegisterPaymentDTO
import com.gabrielcora.pix.payment.application.dto.UpdatePaymentDTO
import com.gabrielcora.pix.payment.domain.commands.results.DeletePaymentCommandResult
import com.gabrielcora.pix.payment.domain.commands.results.PatchPaymentCommandResult
import com.gabrielcora.pix.payment.domain.commands.results.RegisterNewPaymentCommandResult
import com.gabrielcora.pix.payment.domain.commands.results.UpdatePaymentCommandResult

interface IPaymentWriteAppService {
    suspend fun register(payment: RegisterPaymentDTO): RegisterNewPaymentCommandResult
    suspend fun update(id: String, payment: UpdatePaymentDTO): UpdatePaymentCommandResult
    suspend fun patch(id: String, payment: PatchPaymentDTO): PatchPaymentCommandResult
    suspend fun delete(id: String): DeletePaymentCommandResult
}