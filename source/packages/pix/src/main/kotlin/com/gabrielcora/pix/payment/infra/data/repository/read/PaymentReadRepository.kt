package com.gabrielcora.pix.payment.infra.data.repository.read

import com.gabrielcora.pix.payment.infra.data.interfaces.IMongoContext
import com.gabrielcora.pix.payment.domain.interfaces.repository.read.IPaymentReadRepository
import com.gabrielcora.pix.payment.domain.models.Payment
import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum
import org.bson.types.ObjectId
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
class PaymentReadRepository(context: IMongoContext) : IPaymentReadRepository {
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
    override suspend fun findAll(): Iterable<Payment> {
        try {
            return dbSet.find(
                Query().with(
                    Sort.by(Sort.Direction.DESC, "inclusionDate")
                ),
                Payment::class.java)
        } catch (ex: Exception) {
            throw ex
        }
    }
    override suspend fun findByStatus(status: StatusEnum): Iterable<Payment> {
        try {
            return dbSet.find(
                Query(
                    Criteria.where("status").`is`(status)
                ).with(
                    Sort.by(Sort.Direction.DESC, "inclusionDate")
                ),
                Payment::class.java)
        } catch (ex: Exception) {
            throw ex
        }
    }
}