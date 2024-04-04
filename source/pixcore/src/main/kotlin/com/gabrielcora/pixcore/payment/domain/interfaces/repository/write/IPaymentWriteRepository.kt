package com.gabrielcora.pixcore.payment.domain.interfaces.repository.write

import com.gabrielcora.cqrspackage.infra.data.interfaces.IUnitOfWork
import com.gabrielcora.pixcore.payment.domain.models.Payment

interface IPaymentWriteRepository {
    val unitOfWork: IUnitOfWork
    fun save(payment: Payment)
}