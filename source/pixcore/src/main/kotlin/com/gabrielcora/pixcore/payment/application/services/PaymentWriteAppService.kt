package com.gabrielcora.pixcore.payment.application.services;

import com.gabrielcora.pixcore.payment.application.dto.RegisterPaymentDTO;
import com.gabrielcora.pixcore.payment.application.dto.UpdatePaymentDTO;
import com.gabrielcora.pixcore.payment.application.services.interfaces.IPaymentWriteAppService;
import com.gabrielcora.pixcore.payment.domain.commands.PatchPaymentCommand
import com.gabrielcora.pixcore.payment.domain.commands.RegisterNewPaymentCommand
import com.gabrielcora.pixcore.payment.domain.commands.UpdatePaymentCommand
import com.gabrielcora.pixcore.payment.domain.commands.results.PatchPaymentCommandResult
import com.gabrielcora.pixcore.payment.domain.commands.results.RegisterNewPaymentCommandResult
import com.gabrielcora.pixcore.payment.domain.commands.results.UpdatePaymentCommandResult
import com.gabrielcora.pixcore.payment.infra.crosscutting.providers.CommandHandlerProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service;

@Service
class PaymentWriteAppService @Autowired constructor(private val commandHandler: CommandHandlerProvider) : IPaymentWriteAppService {

    override suspend fun register(payment: RegisterPaymentDTO): RegisterNewPaymentCommandResult {
        return commandHandler.handleRegisterNewPayment(
            RegisterNewPaymentCommand(
                payment.status,
                payment.dataInclusao,
                payment.dataPagamento,
                payment.valorPagamento,
                payment.descricaoPagamento,
                payment.dadosRecorrencia,
                payment.destinoPagamento
            )
        )
    }

    override suspend fun update(id: String, payment: UpdatePaymentDTO): UpdatePaymentCommandResult {
        return commandHandler.handleUpdatePayment(
            UpdatePaymentCommand(
                id,
                payment.status,
                payment.dataInclusao,
                payment.dataPagamento,
                payment.valorPagamento,
                payment.descricaoPagamento,
                payment.dadosRecorrencia,
                payment.destinoPagamento
            )
        )
    }

    override suspend fun patch(id: String, payment: Map<String, Any>): PatchPaymentCommandResult {
        return commandHandler.handlePatchPayment(
            PatchPaymentCommand(
                id,
                payment
            )
        )
    }
}
