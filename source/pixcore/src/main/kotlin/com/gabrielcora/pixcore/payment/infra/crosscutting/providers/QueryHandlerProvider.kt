package com.gabrielcora.pixcore.payment.infra.crosscutting.providers

import com.gabrielcora.pixcore.payment.domain.models.Payment
import com.gabrielcora.pixcore.payment.domain.queries.GetPaymentByIdQuery
import com.gabrielcora.pixcore.payment.domain.queries.handlers.GetPaymentByIdQueryHandler
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class QueryHandlerProvider (private val applicationContext: ApplicationContext) {
    suspend fun handleGetPaymentById(query: GetPaymentByIdQuery): Payment? {
        return applicationContext.getBean(GetPaymentByIdQueryHandler::class.java).handle(query)
    }
}