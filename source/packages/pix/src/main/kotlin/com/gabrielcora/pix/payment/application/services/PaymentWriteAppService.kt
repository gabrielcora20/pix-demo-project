package com.gabrielcora.pix.payment.application.services;

import com.gabrielcora.pix.payment.application.dto.PatchPaymentDTO
import com.gabrielcora.pix.payment.application.dto.RegisterPaymentDTO;
import com.gabrielcora.pix.payment.application.dto.UpdatePaymentDTO;
import com.gabrielcora.pix.payment.application.services.interfaces.IPaymentWriteAppService;
import com.gabrielcora.pix.payment.domain.commands.DeletePaymentCommand
import com.gabrielcora.pix.payment.domain.commands.PatchPaymentCommand
import com.gabrielcora.pix.payment.domain.commands.RegisterNewPaymentCommand
import com.gabrielcora.pix.payment.domain.commands.UpdatePaymentCommand
import com.gabrielcora.pix.payment.domain.commands.results.DeletePaymentCommandResult
import com.gabrielcora.pix.payment.domain.commands.results.PatchPaymentCommandResult
import com.gabrielcora.pix.payment.domain.commands.results.RegisterNewPaymentCommandResult
import com.gabrielcora.pix.payment.domain.commands.results.UpdatePaymentCommandResult
import com.gabrielcora.pix.payment.infra.crosscutting.providers.CommandHandlerProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service;

@Service
class PaymentWriteAppService @Autowired constructor(private val commandHandler: CommandHandlerProvider) : IPaymentWriteAppService {

    override suspend fun register(payment: RegisterPaymentDTO): RegisterNewPaymentCommandResult {
        return commandHandler.handleRegisterNewPayment(
            RegisterNewPaymentCommand(
                payment.paymentDate,
                payment.value,
                payment.description,
                payment.recurrence,
                payment.destination
            )
        )
    }

    override suspend fun update(id: String, payment: UpdatePaymentDTO): UpdatePaymentCommandResult {
        return commandHandler.handleUpdatePayment(
            UpdatePaymentCommand(
                id,
                payment.paymentDate,
                payment.value,
                payment.description,
                payment.recurrence,
                payment.destination
            )
        )
    }

    override suspend fun patch(id: String, payment: PatchPaymentDTO): PatchPaymentCommandResult {
        return commandHandler.handlePatchPayment(
            PatchPaymentCommand(
                id,
                payment
            )
        )
    }

    override suspend fun delete(id: String): DeletePaymentCommandResult {
        return commandHandler.handleDeletePayment(
            DeletePaymentCommand(id)
        )
    }
}
