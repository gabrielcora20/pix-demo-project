package com.gabrielcora.pix.payment.domain.commands.handlers

import com.gabrielcora.pix.payment.domain.commands.UpdatePaymentCommand
import com.gabrielcora.pix.payment.domain.commands.results.UpdatePaymentCommandResult
import com.gabrielcora.pix.payment.domain.models.Payment
import com.gabrielcora.pix.payment.domain.interfaces.repository.write.IPaymentWriteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UpdatePaymentCommandHandler(
    @Autowired val paymentWriteRepository: IPaymentWriteRepository
): CommandHandler<UpdatePaymentCommandResult, UpdatePaymentCommand>() {
    override suspend fun handle(command: UpdatePaymentCommand): UpdatePaymentCommandResult {
        val paymentToUpdate = Payment()
        paymentToUpdate.handle(command)
        paymentWriteRepository.save(paymentToUpdate)
        commit(paymentWriteRepository.unitOfWork)
        return UpdatePaymentCommandResult(true)
    }
}