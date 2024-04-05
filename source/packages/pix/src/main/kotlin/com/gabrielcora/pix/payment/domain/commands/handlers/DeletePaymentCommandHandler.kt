package com.gabrielcora.pix.payment.domain.commands.handlers

import com.gabrielcora.pix.payment.domain.commands.DeletePaymentCommand
import com.gabrielcora.pix.payment.domain.commands.results.DeletePaymentCommandResult
import com.gabrielcora.pix.payment.domain.interfaces.repository.read.IPaymentReadRepository
import com.gabrielcora.pix.payment.domain.interfaces.repository.write.IPaymentWriteRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Component

@Component
class DeletePaymentCommandHandler(
    val paymentWriteRepository: IPaymentWriteRepository,
    val paymentReadRepository: IPaymentReadRepository
): CommandHandler<DeletePaymentCommandResult, DeletePaymentCommand>() {
    override suspend fun handle(command: DeletePaymentCommand): DeletePaymentCommandResult {
        val paymentToDelete = paymentReadRepository.findById(command.id) ?: throw NotFoundException()
        paymentToDelete.handle(command)
        paymentWriteRepository.save(paymentToDelete)
        commit(paymentWriteRepository.unitOfWork)
        return DeletePaymentCommandResult(true)
    }
}