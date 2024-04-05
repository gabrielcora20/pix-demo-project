package com.gabrielcora.pix.payment.application.services.interfaces

import com.gabrielcora.pix.payment.domain.models.Payment

interface IPaymentReadAppService {
    suspend fun getAll() : Iterable<Payment?>
    suspend fun getEffected() : Iterable<Payment?>
    suspend fun getScheduled() : Iterable<Payment?>
}