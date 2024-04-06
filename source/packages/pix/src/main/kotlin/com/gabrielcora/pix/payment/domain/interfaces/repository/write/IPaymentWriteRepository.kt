package com.gabrielcora.pix.payment.domain.interfaces.repository.write

import com.gabrielcora.pix.payment.domain.interfaces.IUnitOfWork
import com.gabrielcora.pix.payment.domain.models.Payment

interface IPaymentWriteRepository {
    val unitOfWork: IUnitOfWork
    fun save(payment: Payment)
}