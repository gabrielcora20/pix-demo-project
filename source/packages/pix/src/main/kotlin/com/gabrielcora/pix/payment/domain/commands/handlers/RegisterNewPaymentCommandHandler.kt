package com.gabrielcora.pix.payment.domain.commands.handlers

import com.gabrielcora.pix.payment.domain.commands.RegisterNewPaymentCommand
import com.gabrielcora.pix.payment.domain.commands.results.RegisterNewPaymentCommandResult
import com.gabrielcora.pix.payment.domain.events.PaymentRegisteredEvent
import com.gabrielcora.pix.payment.domain.interfaces.repository.read.PaymentReadRepository
import com.gabrielcora.pix.payment.domain.interfaces.repository.write.PaymentWriteRepository
import com.gabrielcora.pix.payment.domain.models.Destination
import com.gabrielcora.pix.payment.domain.models.Payment
import com.gabrielcora.pix.payment.infra.crosscutting.helpers.interfaces.PixKeyHelper
import org.bson.types.ObjectId
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class RegisterNewPaymentCommandHandler(
    val paymentWriteRepository: PaymentWriteRepository,
    val paymentReadRepository: PaymentReadRepository,
    var pixKeyHelper: PixKeyHelper,
) : CommandHandler<RegisterNewPaymentCommandResult, RegisterNewPaymentCommand>() {
    override suspend fun handle(command: RegisterNewPaymentCommand): RegisterNewPaymentCommandResult {
        val paymentToInsert = Payment()

        paymentToInsert.id = ObjectId()

        val currentDate = LocalDateTime.now()

        paymentToInsert.inclusionDate = currentDate
        paymentToInsert.paymentDate = command.paymentDate ?: currentDate
        paymentToInsert.value = command.value
        paymentToInsert.description = command.description
        paymentToInsert.recurrence = command.recurrence
        paymentToInsert.destination = Destination(command.pixKey, pixKeyHelper.getTypeFromPixKey(command.pixKey))
        paymentToInsert.status = paymentToInsert.getStatusByPaymentDate(currentDate)

        val similarPayments = paymentReadRepository.findSimilarPayments(paymentToInsert)

        paymentToInsert.raise(PaymentRegisteredEvent(paymentToInsert))

        paymentWriteRepository.save(paymentToInsert)
        commit(paymentWriteRepository.unitOfWork)
        return RegisterNewPaymentCommandResult(true, if (similarPayments.count() > 0) listOf("Um pagamento semelhante foi detectado") else null)
    }
}