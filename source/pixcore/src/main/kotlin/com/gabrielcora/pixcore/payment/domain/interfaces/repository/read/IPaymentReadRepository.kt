package com.gabrielcora.pixcore.payment.domain.interfaces.repository.read

import com.gabrielcora.pixcore.payment.domain.models.Payment
import com.gabrielcora.pixcore.payment.domain.models.enums.StatusEnum
import org.bson.types.ObjectId

interface IPaymentReadRepository {
    suspend fun findById(id: ObjectId): Payment?
    suspend fun findById(id: String): Payment?
    suspend fun findAll(): Iterable<Payment>
    suspend fun findByStatus(status: StatusEnum): Iterable<Payment>
}