package com.gabrielcora.pixcore.payment.domain.queries.handlers

import com.gabrielcora.cqrspackage.domain.queries.handlers.QueryHandler
import com.gabrielcora.pixcore.payment.domain.interfaces.repository.read.IPaymentReadRepository
import com.gabrielcora.pixcore.payment.domain.models.Payment
import com.gabrielcora.pixcore.payment.domain.queries.GetAllPaymentsQuery
import org.springframework.stereotype.Component

@Component
class GetAllPaymentsQueryHandler(
    val paymentReadRepository: IPaymentReadRepository
) : QueryHandler<Iterable<Payment>, GetAllPaymentsQuery>() {
    override suspend fun handle(query: GetAllPaymentsQuery): Iterable<Payment> {
        return paymentReadRepository.findAll()
    }
}