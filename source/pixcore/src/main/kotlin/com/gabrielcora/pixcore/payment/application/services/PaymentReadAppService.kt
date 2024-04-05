package com.gabrielcora.pixcore.payment.application.services;

import com.gabrielcora.pixcore.payment.application.services.interfaces.IPaymentReadAppService;
import com.gabrielcora.pixcore.payment.domain.models.Payment
import com.gabrielcora.pixcore.payment.domain.queries.GetPaymentByIdQuery
import com.gabrielcora.pixcore.payment.infra.crosscutting.providers.QueryHandlerProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service;

@Service
class PaymentReadAppService @Autowired constructor(private val queryHandler: QueryHandlerProvider) : IPaymentReadAppService {
    override suspend fun getAll() {
        println("reach here")
    }

    override suspend fun getById(id: String): Payment? {
        return queryHandler.handleGetPaymentById(
            GetPaymentByIdQuery(id)
        )
    }
}
