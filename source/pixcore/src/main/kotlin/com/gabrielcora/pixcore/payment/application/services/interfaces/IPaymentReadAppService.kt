package com.gabrielcora.pixcore.payment.application.services.interfaces

import com.gabrielcora.pixcore.payment.domain.models.Payment

interface IPaymentReadAppService {
    suspend fun getAll() : Iterable<Payment?>
    suspend fun getEffected() : Iterable<Payment?>
    suspend fun getScheduled() : Iterable<Payment?>
}