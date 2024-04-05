package com.gabrielcora.pixcore.payment.domain.interfaces.repository.read

import com.gabrielcora.cqrspackage.infra.data.interfaces.IUnitOfWork
import com.gabrielcora.pixcore.payment.domain.models.Payment
import org.bson.types.ObjectId

interface IPaymentReadRepository {
    suspend fun findById(id: ObjectId): Payment?
    suspend fun findById(id: String): Payment?
}