package com.gabrielcora.pix.payment.domain.commands.handlers

import com.gabrielcora.pix.payment.domain.commands.PatchPaymentCommand
import com.gabrielcora.pix.payment.domain.commands.results.PatchPaymentCommandResult
import com.gabrielcora.pix.payment.domain.interfaces.repository.read.IPaymentReadRepository
import com.gabrielcora.pix.payment.domain.interfaces.repository.write.IPaymentWriteRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Component

@Component
class PatchPaymentCommandHandler(
    val paymentWriteRepository: IPaymentWriteRepository,
    val paymentReadRepository: IPaymentReadRepository
): CommandHandler<PatchPaymentCommandResult, PatchPaymentCommand>() {
    override suspend fun handle(command: PatchPaymentCommand): PatchPaymentCommandResult {
        val paymentToPatch = paymentReadRepository.findById(command.id) ?: throw NotFoundException()
        paymentToPatch.handle(command)
        paymentWriteRepository.save(paymentToPatch)
        commit(paymentWriteRepository.unitOfWork)
        return PatchPaymentCommandResult(true)
    }
}