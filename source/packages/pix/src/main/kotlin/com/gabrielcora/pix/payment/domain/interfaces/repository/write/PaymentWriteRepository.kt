package com.gabrielcora.pix.payment.domain.interfaces.repository.write

import com.gabrielcora.pix.payment.domain.interfaces.UnitOfWork
import com.gabrielcora.pix.payment.domain.models.Payment

interface PaymentWriteRepository {
    val unitOfWork: UnitOfWork
    fun save(payment: Payment)
}