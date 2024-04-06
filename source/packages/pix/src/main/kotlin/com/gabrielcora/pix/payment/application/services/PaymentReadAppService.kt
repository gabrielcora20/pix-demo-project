package com.gabrielcora.pix.payment.application.services

import com.gabrielcora.pix.payment.application.services.interfaces.IPaymentReadAppService
import com.gabrielcora.pix.payment.domain.models.Payment
import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum
import com.gabrielcora.pix.payment.domain.queries.GetAllPaymentsQuery
import com.gabrielcora.pix.payment.domain.queries.GetPaymentsByStatusQuery
import com.gabrielcora.pix.payment.infra.crosscutting.providers.QueryHandlerProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PaymentReadAppService @Autowired constructor(private val queryHandler: QueryHandlerProvider) :
    IPaymentReadAppService {
    override suspend fun getAll(): Iterable<Payment> {
        return queryHandler.handleGetAllPayments(
            GetAllPaymentsQuery()
        )
    }

    override suspend fun getEffected(): Iterable<Payment> {
        return queryHandler.handleGetPaymentsByStatus(
            GetPaymentsByStatusQuery(StatusEnum.EFFECTED)
        )
    }

    override suspend fun getScheduled(): Iterable<Payment> {
        return queryHandler.handleGetPaymentsByStatus(
            GetPaymentsByStatusQuery(StatusEnum.SCHEDULED)
        )
    }
}
