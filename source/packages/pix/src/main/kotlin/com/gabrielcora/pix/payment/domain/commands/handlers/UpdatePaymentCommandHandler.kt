package com.gabrielcora.pix.payment.domain.commands.handlers

import com.gabrielcora.pix.payment.domain.commands.UpdatePaymentCommand
import com.gabrielcora.pix.payment.domain.commands.results.UpdatePaymentCommandResult
import com.gabrielcora.pix.payment.domain.events.PaymentUpdatedEvent
import com.gabrielcora.pix.payment.domain.exceptions.PaymentNotFoundException
import com.gabrielcora.pix.payment.domain.interfaces.repository.read.PaymentReadRepository
import com.gabrielcora.pix.payment.domain.interfaces.repository.write.PaymentWriteRepository
import com.gabrielcora.pix.payment.domain.models.Destination
import com.gabrielcora.pix.payment.infra.crosscutting.helpers.interfaces.PixKeyHelper
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class UpdatePaymentCommandHandler(
    val paymentWriteRepository: PaymentWriteRepository,
    val paymentReadRepository: PaymentReadRepository,
    var pixKeyHelper: PixKeyHelper,
) : CommandHandler<UpdatePaymentCommandResult, UpdatePaymentCommand>() {
    override suspend fun handle(command: UpdatePaymentCommand): UpdatePaymentCommandResult {
        val paymentToUpdate = paymentReadRepository.findById(command.id)
            ?: throw PaymentNotFoundException("Não foi encontrado nenhum pagamento com o id ${command.id}")

        val currentDate = LocalDateTime.now()

        paymentToUpdate.paymentDate = command.paymentDate ?: currentDate
        paymentToUpdate.value = command.value
        paymentToUpdate.description = command.description
        paymentToUpdate.recurrence = command.recurrence
        paymentToUpdate.destination = Destination(command.pixKey, pixKeyHelper.getTypeFromPixKey(command.pixKey))
        paymentToUpdate.status = paymentToUpdate.getStatusByPaymentDate(currentDate)

        val similarPayments = paymentReadRepository.findSimilarPayments(paymentToUpdate)

        paymentToUpdate.raise(PaymentUpdatedEvent(paymentToUpdate))

        paymentWriteRepository.save(paymentToUpdate)
        commit(paymentWriteRepository.unitOfWork)
        return UpdatePaymentCommandResult(true, if (similarPayments.count() > 0) listOf("Um pagamento semelhante foi detectado") else null)
    }
}