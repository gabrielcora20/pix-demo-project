package com.gabrielcora.pixcore.payment.infra.data.repository.write

import com.gabrielcora.pixcore.payment.domain.models.Payment
import com.gabrielcora.pixcore.payment.infra.data.interfaces.IMongoContext
import com.gabrielcora.pixcore.payment.domain.interfaces.repository.write.IPaymentWriteRepository
import com.gabrielcora.pixcore.payment.infra.data.interfaces.IUnitOfWork
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import kotlinx.coroutines.runBlocking
import org.bson.Document
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository

@Repository
class PaymentWriteRepository(private val context: IMongoContext) : IPaymentWriteRepository {
    protected val dbSet: MongoTemplate = context.db
    override val unitOfWork: IUnitOfWork = context

    override fun save(payment: Payment) {
        try {
            runBlocking {
                context.addCommand({ dbSet.save(payment) }, payment)
            }
        } catch (ex: Exception) {
            throw ex
        }
    }
}