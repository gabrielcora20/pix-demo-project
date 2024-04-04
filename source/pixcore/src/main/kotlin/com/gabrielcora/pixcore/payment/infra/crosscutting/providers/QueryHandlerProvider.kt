package com.gabrielcora.pixcore.payment.infra.crosscutting.providers

import com.gabrielcora.pixcore.payment.domain.commands.RegisterNewPaymentCommand
import com.gabrielcora.pixcore.payment.domain.commands.handlers.RegisterNewPaymentCommandHandler
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class QueryHandlerProvider constructor(private val applicationContext: ApplicationContext) {

}