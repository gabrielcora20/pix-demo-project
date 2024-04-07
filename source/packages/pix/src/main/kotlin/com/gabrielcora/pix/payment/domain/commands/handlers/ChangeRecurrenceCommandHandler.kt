package com.gabrielcora.pix.payment.domain.commands.handlers

import com.gabrielcora.pix.payment.domain.commands.ChangeRecurrenceCommand
import com.gabrielcora.pix.payment.domain.commands.results.ChangeRecurrenceCommandResult
import com.gabrielcora.pix.payment.domain.events.RecurrenceChangedEvent
import com.gabrielcora.pix.payment.domain.exceptions.PaymentNotFoundException
import com.gabrielcora.pix.payment.domain.interfaces.repository.read.IPaymentReadRepository
import com.gabrielcora.pix.payment.domain.interfaces.repository.write.IPaymentWriteRepository
import org.springframework.stereotype.Component

@Component
class ChangeRecurrenceCommandHandler(
    val paymentWriteRepository: IPaymentWriteRepository,
    val paymentReadRepository: IPaymentReadRepository,
) : CommandHandler<ChangeRecurrenceCommandResult, ChangeRecurrenceCommand>() {
    override suspend fun handle(command: ChangeRecurrenceCommand): ChangeRecurrenceCommandResult {
        val paymentToUpdate = paymentReadRepository.findById(command.id)
            ?: throw PaymentNotFoundException("Não foi encontrado nenhum pagamento com o id ${command.id}")

        paymentToUpdate.recurrence = command.recurrence

        paymentToUpdate.raise(RecurrenceChangedEvent(paymentToUpdate.id!!))

        paymentWriteRepository.save(paymentToUpdate)
        commit(paymentWriteRepository.unitOfWork)
        return ChangeRecurrenceCommandResult(true)
    }
}