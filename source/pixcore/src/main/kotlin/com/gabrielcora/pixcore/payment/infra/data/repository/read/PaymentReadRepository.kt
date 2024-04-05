package com.gabrielcora.pixcore.payment.infra.data.repository.read

import com.gabrielcora.cqrspackage.infra.data.interfaces.IMongoContext
import com.gabrielcora.pixcore.payment.domain.interfaces.repository.read.IPaymentReadRepository
import com.gabrielcora.pixcore.payment.domain.models.Payment
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository

@Repository
class PaymentReadRepository(private val context: IMongoContext) : IPaymentReadRepository {
    protected val dbSet: MongoTemplate = context.db

    override suspend fun findById(id: ObjectId): Payment? {
        try {
            return dbSet.findById(id, Payment::class.java)
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun findById(id: String): Payment? {
        try {
            return dbSet.findById(ObjectId(id), Payment::class.java)
        } catch (ex: Exception) {
            throw ex
        }
    }
}