package com.gabrielcora.pixcore.payment.infra.crosscutting.providers

import com.gabrielcora.pixcore.payment.domain.models.Payment
import com.gabrielcora.pixcore.payment.domain.queries.GetAllPaymentsQuery
import com.gabrielcora.pixcore.payment.domain.queries.GetPaymentsByStatusQuery
import com.gabrielcora.pixcore.payment.domain.queries.handlers.GetAllPaymentsQueryHandler
import com.gabrielcora.pixcore.payment.domain.queries.handlers.GetPaymentsByStatusQueryHandler
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class QueryHandlerProvider (private val applicationContext: ApplicationContext) {
    suspend fun handleGetAllPayments(query: GetAllPaymentsQuery): Iterable<Payment> {
        return applicationContext.getBean(GetAllPaymentsQueryHandler::class.java).handle(query)
    }
    suspend fun handleGetPaymentsByStatus(query: GetPaymentsByStatusQuery): Iterable<Payment> {
        return applicationContext.getBean(GetPaymentsByStatusQueryHandler::class.java).handle(query)
    }
}