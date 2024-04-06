package com.gabrielcora.pix.payment.domain.commands.handlers

import com.gabrielcora.pix.payment.domain.commands.PatchPaymentCommand
import com.gabrielcora.pix.payment.domain.commands.results.PatchPaymentCommandResult
import com.gabrielcora.pix.payment.domain.events.PaymentPatchedEvent
import com.gabrielcora.pix.payment.domain.interfaces.repository.read.IPaymentReadRepository
import com.gabrielcora.pix.payment.domain.interfaces.repository.write.IPaymentWriteRepository
import com.gabrielcora.pix.payment.infra.crosscutting.helpers.interfaces.ObjectMergeHelper
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Component

@Component
class PatchPaymentCommandHandler(
    val paymentWriteRepository: IPaymentWriteRepository,
    val paymentReadRepository: IPaymentReadRepository,
    val objectMergeHelper: ObjectMergeHelper
): CommandHandler<PatchPaymentCommandResult, PatchPaymentCommand>() {
    override suspend fun handle(command: PatchPaymentCommand): PatchPaymentCommandResult {
        val paymentToPatch = paymentReadRepository.findById(command.id) ?: throw NotFoundException()

        val paymentPatched = objectMergeHelper.merge(paymentToPatch, command.payment)

        paymentPatched.raise(PaymentPatchedEvent(paymentPatched.id!!))

        paymentWriteRepository.save(paymentPatched)
        commit(paymentWriteRepository.unitOfWork)
        return PatchPaymentCommandResult(true)
    }
}