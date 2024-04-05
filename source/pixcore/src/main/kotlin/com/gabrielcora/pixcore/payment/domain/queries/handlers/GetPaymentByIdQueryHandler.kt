package com.gabrielcora.pixcore.payment.domain.queries.handlers

import com.gabrielcora.cqrspackage.domain.queries.handlers.QueryHandler
import com.gabrielcora.pixcore.payment.domain.interfaces.repository.read.IPaymentReadRepository
import com.gabrielcora.pixcore.payment.domain.models.Payment
import com.gabrielcora.pixcore.payment.domain.queries.GetPaymentByIdQuery
import org.springframework.stereotype.Component

@Component
class GetPaymentByIdQueryHandler(
    val paymentReadRepository: IPaymentReadRepository
) : QueryHandler<Payment?, GetPaymentByIdQuery>() {
    override suspend fun handle(query: GetPaymentByIdQuery): Payment? {
        return paymentReadRepository.findById(query.id)
    }
}