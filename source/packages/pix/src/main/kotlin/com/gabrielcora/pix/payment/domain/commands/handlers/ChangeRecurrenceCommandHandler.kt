package com.gabrielcora.pix.payment.domain.commands.handlers

import com.gabrielcora.pix.payment.domain.commands.ChangeRecurrenceCommand
import com.gabrielcora.pix.payment.domain.commands.results.ChangeRecurrenceCommandResult
import com.gabrielcora.pix.payment.domain.events.RecurrenceChangedEvent
import com.gabrielcora.pix.payment.domain.exceptions.PaymentNotFoundException
import com.gabrielcora.pix.payment.domain.interfaces.repository.read.PaymentReadRepository
import com.gabrielcora.pix.payment.domain.interfaces.repository.write.PaymentWriteRepository
import org.springframework.stereotype.Component

@Component
class ChangeRecurrenceCommandHandler(
    val paymentWriteRepository: PaymentWriteRepository,
    val paymentReadRepository: PaymentReadRepository,
) : CommandHandler<ChangeRecurrenceCommandResult, ChangeRecurrenceCommand>() {
    override suspend fun handle(command: ChangeRecurrenceCommand): ChangeRecurrenceCommandResult {
        val paymentToUpdate = paymentReadRepository.findById(command.id)
            ?: throw PaymentNotFoundException("Não foi encontrado nenhum pagamento com o id ${command.id}")

        paymentToUpdate.recurrence = command.recurrence

        paymentToUpdate.raise(RecurrenceChangedEvent(paymentToUpdate))

        paymentWriteRepository.save(paymentToUpdate)
        commit(paymentWriteRepository.unitOfWork)
        return ChangeRecurrenceCommandResult(true)
    }
}