package com.gabrielcora.pixcore.payment.application.services;

import com.gabrielcora.pixcore.payment.application.services.interfaces.IPaymentReadAppService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
class PaymentReadAppService : IPaymentReadAppService {
    override fun getAll() {
        println("reach here")
    }

    override fun getById(id: String) {
        TODO("Not yet implemented")
    }
}
