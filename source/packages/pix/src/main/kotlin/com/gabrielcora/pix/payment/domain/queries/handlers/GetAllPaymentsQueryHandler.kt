package com.gabrielcora.pix.payment.domain.queries.handlers

import com.gabrielcora.pix.payment.domain.interfaces.repository.read.PaymentReadRepository
import com.gabrielcora.pix.payment.domain.models.Payment
import com.gabrielcora.pix.payment.domain.queries.GetAllPaymentsQuery
import org.springframework.stereotype.Component

@Component
class GetAllPaymentsQueryHandler(
    val paymentReadRepository: PaymentReadRepository,
) : QueryHandler<Iterable<Payment>, GetAllPaymentsQuery>() {
    override suspend fun handle(query: GetAllPaymentsQuery): Iterable<Payment> {
        return paymentReadRepository.findAll()
    }
}