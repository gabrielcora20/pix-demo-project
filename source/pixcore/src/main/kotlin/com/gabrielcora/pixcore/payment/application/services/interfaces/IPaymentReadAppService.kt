package com.gabrielcora.pixcore.payment.application.services.interfaces

import com.gabrielcora.pixcore.payment.domain.models.Payment

interface IPaymentReadAppService {
    suspend fun getAll()
    suspend fun getById(id: String): Payment?
}