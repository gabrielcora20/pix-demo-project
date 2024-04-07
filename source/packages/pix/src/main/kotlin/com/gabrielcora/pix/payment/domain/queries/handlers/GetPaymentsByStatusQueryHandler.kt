package com.gabrielcora.pix.payment.domain.queries.handlers

import com.gabrielcora.pix.payment.domain.interfaces.repository.read.PaymentReadRepository
import com.gabrielcora.pix.payment.domain.models.Payment
import com.gabrielcora.pix.payment.domain.queries.GetPaymentsByStatusQuery
import org.springframework.stereotype.Component

@Component
class GetPaymentsByStatusQueryHandler(
    val paymentReadRepository: PaymentReadRepository,
) : QueryHandler<Iterable<Payment>, GetPaymentsByStatusQuery>() {
    override suspend fun handle(query: GetPaymentsByStatusQuery): Iterable<Payment> {
        return paymentReadRepository.findByStatus(query.status)
    }
}