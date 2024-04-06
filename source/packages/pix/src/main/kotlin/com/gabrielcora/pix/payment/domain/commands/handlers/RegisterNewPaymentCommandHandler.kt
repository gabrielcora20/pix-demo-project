package com.gabrielcora.pix.payment.domain.commands.handlers

import com.gabrielcora.pix.payment.domain.commands.RegisterNewPaymentCommand
import com.gabrielcora.pix.payment.domain.commands.results.RegisterNewPaymentCommandResult
import com.gabrielcora.pix.payment.domain.events.PaymentRegisteredEvent
import com.gabrielcora.pix.payment.domain.models.Payment
import com.gabrielcora.pix.payment.domain.interfaces.repository.write.IPaymentWriteRepository
import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum
import org.bson.types.ObjectId
import org.springframework.stereotype.Component
import java.util.*

@Component
class RegisterNewPaymentCommandHandler(
    val paymentWriteRepository: IPaymentWriteRepository
): CommandHandler<RegisterNewPaymentCommandResult, RegisterNewPaymentCommand>() {
    override suspend fun handle(command: RegisterNewPaymentCommand): RegisterNewPaymentCommandResult {
        val paymentToInsert = Payment()

        paymentToInsert.id = ObjectId()
        paymentToInsert.status = StatusEnum.EFFECTED
        paymentToInsert.inclusionDate = Date()
        paymentToInsert.paymentDate = command.paymentDate
        paymentToInsert.value = command.value
        paymentToInsert.description = command.description
        paymentToInsert.recurrence = command.recurrence
        paymentToInsert.destination = command.destination

        paymentToInsert.raise(PaymentRegisteredEvent(paymentToInsert.id!!))

        paymentWriteRepository.save(paymentToInsert)
        commit(paymentWriteRepository.unitOfWork)
        return RegisterNewPaymentCommandResult(true)
    }
}