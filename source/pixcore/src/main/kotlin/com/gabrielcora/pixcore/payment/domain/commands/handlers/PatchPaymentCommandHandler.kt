package com.gabrielcora.pixcore.payment.domain.commands.handlers

import com.gabrielcora.cqrspackage.domain.commands.handlers.CommandHandler
import com.gabrielcora.pixcore.payment.domain.commands.PatchPaymentCommand
import com.gabrielcora.pixcore.payment.domain.commands.results.PatchPaymentCommandResult
import com.gabrielcora.pixcore.payment.domain.interfaces.repository.read.IPaymentReadRepository
import com.gabrielcora.pixcore.payment.domain.interfaces.repository.write.IPaymentWriteRepository
import org.bson.types.ObjectId
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Component

@Component
class PatchPaymentCommandHandler(
    val paymentWriteRepository: IPaymentWriteRepository,
    val paymentReadRepository: IPaymentReadRepository
): CommandHandler<PatchPaymentCommandResult, PatchPaymentCommand>() {
    override suspend fun handle(command: PatchPaymentCommand): PatchPaymentCommandResult {
        val paymentToPatch = paymentReadRepository.findById(ObjectId( command.id)) ?: throw NotFoundException()
        paymentToPatch.handle(command)
        paymentWriteRepository.save(paymentToPatch)
        commit(paymentWriteRepository.unitOfWork)
        return PatchPaymentCommandResult(true)
    }
}