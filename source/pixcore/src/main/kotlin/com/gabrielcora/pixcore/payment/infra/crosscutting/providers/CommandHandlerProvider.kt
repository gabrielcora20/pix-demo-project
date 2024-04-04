package com.gabrielcora.pixcore.payment.infra.crosscutting.providers

import com.gabrielcora.pixcore.payment.domain.commands.PartiallyUpdatePaymentCommand
import com.gabrielcora.pixcore.payment.domain.commands.RegisterNewPaymentCommand
import com.gabrielcora.pixcore.payment.domain.commands.UpdatePaymentCommand
import com.gabrielcora.pixcore.payment.domain.commands.handlers.PartiallyUpdatePaymentCommandHandler
import com.gabrielcora.pixcore.payment.domain.commands.handlers.RegisterNewPaymentCommandHandler
import com.gabrielcora.pixcore.payment.domain.commands.handlers.UpdatePaymentCommandHandler
import com.gabrielcora.pixcore.payment.domain.commands.results.PartiallyUpdatePaymentCommandResult
import com.gabrielcora.pixcore.payment.domain.commands.results.RegisterNewPaymentCommandResult
import com.gabrielcora.pixcore.payment.domain.commands.results.UpdatePaymentCommandResult
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class CommandHandlerProvider(private val applicationContext: ApplicationContext) {
    suspend fun handleRegisterNewPayment(cmd: RegisterNewPaymentCommand): RegisterNewPaymentCommandResult {
        return applicationContext.getBean(RegisterNewPaymentCommandHandler::class.java).handle(cmd)
    }

    suspend fun handleUpdatePayment(cmd: UpdatePaymentCommand): UpdatePaymentCommandResult {
        return applicationContext.getBean(UpdatePaymentCommandHandler::class.java).handle(cmd)
    }

    suspend fun handlePartiallyUpdatePayment(cmd: PartiallyUpdatePaymentCommand): PartiallyUpdatePaymentCommandResult {
        return applicationContext.getBean(PartiallyUpdatePaymentCommandHandler::class.java).handle(cmd)
    }
}