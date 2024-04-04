package com.gabrielcora.pixcore.payment.domain.commands.handlers

import com.gabrielcora.cqrspackage.domain.commands.handlers.CommandHandler
import com.gabrielcora.pixcore.payment.domain.commands.PartiallyUpdatePaymentCommand
import com.gabrielcora.pixcore.payment.domain.commands.results.PartiallyUpdatePaymentCommandResult
import com.gabrielcora.pixcore.payment.domain.models.Payment
import com.gabrielcora.pixcore.payment.domain.interfaces.repository.write.IPaymentWriteRepository
import org.springframework.stereotype.Component

@Component
class PartiallyUpdatePaymentCommandHandler(
    val paymentWriteRepository: IPaymentWriteRepository
): CommandHandler<PartiallyUpdatePaymentCommandResult, PartiallyUpdatePaymentCommand>() {
    override suspend fun handle(command: PartiallyUpdatePaymentCommand): PartiallyUpdatePaymentCommandResult {
        val paymentToPartiallyUpdate = Payment()
        paymentToPartiallyUpdate.handle(command)
        paymentWriteRepository.save(paymentToPartiallyUpdate)
        commit(paymentWriteRepository.unitOfWork)
        return PartiallyUpdatePaymentCommandResult(true)
    }
}