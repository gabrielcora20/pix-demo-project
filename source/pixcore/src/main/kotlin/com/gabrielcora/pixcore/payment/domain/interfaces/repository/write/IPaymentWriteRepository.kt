package com.gabrielcora.pixcore.payment.domain.interfaces.repository.write

import com.gabrielcora.pixcore.payment.domain.models.Payment
import com.gabrielcora.pixcore.payment.infra.data.interfaces.IUnitOfWork

interface IPaymentWriteRepository {
    val unitOfWork: IUnitOfWork
    fun save(payment: Payment)
}