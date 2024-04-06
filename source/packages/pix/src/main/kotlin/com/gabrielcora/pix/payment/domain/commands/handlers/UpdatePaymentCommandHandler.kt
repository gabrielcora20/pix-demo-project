package com.gabrielcora.pix.payment.domain.commands.handlers

import com.gabrielcora.pix.payment.domain.commands.UpdatePaymentCommand
import com.gabrielcora.pix.payment.domain.commands.results.UpdatePaymentCommandResult
import com.gabrielcora.pix.payment.domain.events.PaymentUpdatedEvent
import com.gabrielcora.pix.payment.domain.interfaces.repository.read.IPaymentReadRepository
import com.gabrielcora.pix.payment.domain.models.Payment
import com.gabrielcora.pix.payment.domain.interfaces.repository.write.IPaymentWriteRepository
import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Component

@Component
class UpdatePaymentCommandHandler(
    val paymentWriteRepository: IPaymentWriteRepository,
    val paymentReadRepository: IPaymentReadRepository,
): CommandHandler<UpdatePaymentCommandResult, UpdatePaymentCommand>() {
    override suspend fun handle(command: UpdatePaymentCommand): UpdatePaymentCommandResult {
        val paymentToUpdate = paymentReadRepository.findById(command.id) ?: throw ChangeSetPersister.NotFoundException()

        paymentToUpdate.status = StatusEnum.EFFECTED
        paymentToUpdate.paymentDate = command.paymentDate
        paymentToUpdate.value = command.value
        paymentToUpdate.description = command.description
        paymentToUpdate.recurrence = command.recurrence
        paymentToUpdate.destination = command.destination

        paymentToUpdate.raise(PaymentUpdatedEvent(paymentToUpdate.id!!))

        paymentWriteRepository.save(paymentToUpdate)
        commit(paymentWriteRepository.unitOfWork)
        return UpdatePaymentCommandResult(true)
    }
}