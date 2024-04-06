package com.gabrielcora.pix.payment.domain.commands.handlers

import com.gabrielcora.pix.payment.domain.commands.PatchPaymentCommand
import com.gabrielcora.pix.payment.domain.commands.results.PatchPaymentCommandResult
import com.gabrielcora.pix.payment.domain.events.PaymentPatchedEvent
import com.gabrielcora.pix.payment.domain.exceptions.PaymentNotFoundException
import com.gabrielcora.pix.payment.domain.interfaces.repository.read.IPaymentReadRepository
import com.gabrielcora.pix.payment.domain.interfaces.repository.write.IPaymentWriteRepository
import com.gabrielcora.pix.payment.domain.models.Destination
import com.gabrielcora.pix.payment.infra.crosscutting.helpers.interfaces.ObjectMergeHelper
import com.gabrielcora.pix.payment.infra.crosscutting.helpers.interfaces.PixKeyHelper
import org.springframework.stereotype.Component
import java.util.*

@Component
class PatchPaymentCommandHandler(
    val paymentWriteRepository: IPaymentWriteRepository,
    val paymentReadRepository: IPaymentReadRepository,
    val objectMergeHelper: ObjectMergeHelper,
    var pixKeyHelper: PixKeyHelper,
) : CommandHandler<PatchPaymentCommandResult, PatchPaymentCommand>() {
    override suspend fun handle(command: PatchPaymentCommand): PatchPaymentCommandResult {
        val paymentToPatch = paymentReadRepository.findById(command.id)
            ?: throw PaymentNotFoundException("Não foi encontrado nenhum pagamento com o id ${command.id}")

        val pixKey = command.payment.getOrDefault("pixKey", null).toString()

        val paymentPatched = objectMergeHelper.merge(paymentToPatch, command.payment.filter { it -> it.key != "pixKey" })

        val currentDate = Date()

        if (command.payment.containsKey("pixKey"))
            paymentPatched.destination = Destination(pixKey, pixKeyHelper.getTypeFromPixKey(pixKey))

        if (command.payment.containsKey("paymentDate"))
            paymentPatched.status = paymentPatched.getStatusByPaymentDate(currentDate)

        paymentPatched.raise(PaymentPatchedEvent(paymentPatched.id!!))

        paymentWriteRepository.save(paymentPatched)
        commit(paymentWriteRepository.unitOfWork)
        return PatchPaymentCommandResult(true)
    }
}