package com.gabrielcora.pix.payment.domain.interfaces.repository.read

import com.gabrielcora.pix.payment.domain.models.Payment
import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum
import org.bson.types.ObjectId

interface IPaymentReadRepository {
    suspend fun findById(id: ObjectId): Payment?
    suspend fun findById(id: String): Payment?
    suspend fun findAll(): Iterable<Payment>
    suspend fun findByStatus(status: StatusEnum): Iterable<Payment>
    suspend fun findSimilarPayments(payment: Payment): Iterable<Payment>
}