package com.gabrielcora.pix.payment.domain.commands.handlers

import com.gabrielcora.pix.payment.domain.commands.RegisterNewPaymentCommand
import com.gabrielcora.pix.payment.domain.commands.results.RegisterNewPaymentCommandResult
import com.gabrielcora.pix.payment.domain.models.Payment
import com.gabrielcora.pix.payment.domain.interfaces.repository.write.IPaymentWriteRepository
import org.springframework.stereotype.Component

@Component
class RegisterNewPaymentCommandHandler(
    val paymentWriteRepository: IPaymentWriteRepository
): CommandHandler<RegisterNewPaymentCommandResult, RegisterNewPaymentCommand>() {
    override suspend fun handle(command: RegisterNewPaymentCommand): RegisterNewPaymentCommandResult {
        val paymentToInsert = Payment()
        paymentToInsert.handle(command)
        paymentWriteRepository.save(paymentToInsert)
        commit(paymentWriteRepository.unitOfWork)
        return RegisterNewPaymentCommandResult(true)
    }
}