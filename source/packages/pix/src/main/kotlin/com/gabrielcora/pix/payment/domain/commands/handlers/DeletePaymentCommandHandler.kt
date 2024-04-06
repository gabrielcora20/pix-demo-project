package com.gabrielcora.pix.payment.domain.commands.handlers

import com.gabrielcora.pix.payment.domain.commands.DeletePaymentCommand
import com.gabrielcora.pix.payment.domain.commands.results.DeletePaymentCommandResult
import com.gabrielcora.pix.payment.domain.events.PaymentDeletedEvent
import com.gabrielcora.pix.payment.domain.exceptions.PaymentNotFoundException
import com.gabrielcora.pix.payment.domain.interfaces.repository.read.IPaymentReadRepository
import com.gabrielcora.pix.payment.domain.interfaces.repository.write.IPaymentWriteRepository
import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum
import org.springframework.stereotype.Component

@Component
class DeletePaymentCommandHandler(
    val paymentWriteRepository: IPaymentWriteRepository,
    val paymentReadRepository: IPaymentReadRepository,
) : CommandHandler<DeletePaymentCommandResult, DeletePaymentCommand>() {
    override suspend fun handle(command: DeletePaymentCommand): DeletePaymentCommandResult {
        val paymentToDelete = paymentReadRepository.findById(command.id)
            ?: throw PaymentNotFoundException("Não foi encontrado nenhum pagamento com o id ${command.id}")

        paymentToDelete.status = StatusEnum.CANCELED

        paymentToDelete.raise(PaymentDeletedEvent(paymentToDelete.id!!))

        paymentWriteRepository.save(paymentToDelete)
        commit(paymentWriteRepository.unitOfWork)
        return DeletePaymentCommandResult(true)
    }
}