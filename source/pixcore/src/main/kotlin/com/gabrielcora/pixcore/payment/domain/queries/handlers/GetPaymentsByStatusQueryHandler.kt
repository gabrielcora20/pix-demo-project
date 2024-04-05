package com.gabrielcora.pixcore.payment.domain.queries.handlers

import com.gabrielcora.cqrspackage.domain.queries.handlers.QueryHandler
import com.gabrielcora.pixcore.payment.domain.interfaces.repository.read.IPaymentReadRepository
import com.gabrielcora.pixcore.payment.domain.models.Payment
import com.gabrielcora.pixcore.payment.domain.queries.GetPaymentsByStatusQuery
import org.springframework.stereotype.Component

@Component
class GetPaymentsByStatusQueryHandler(
    val paymentReadRepository: IPaymentReadRepository
) : QueryHandler<Iterable<Payment>, GetPaymentsByStatusQuery>() {
    override suspend fun handle(query: GetPaymentsByStatusQuery): Iterable<Payment> {
        return paymentReadRepository.findByStatus(query.status)
    }
}