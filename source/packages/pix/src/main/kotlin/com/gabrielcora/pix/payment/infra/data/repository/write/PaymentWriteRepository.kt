package com.gabrielcora.pix.payment.infra.data.repository.write

import com.gabrielcora.pix.payment.domain.interfaces.IUnitOfWork
import com.gabrielcora.pix.payment.domain.interfaces.repository.write.IPaymentWriteRepository
import com.gabrielcora.pix.payment.domain.models.Payment
import com.gabrielcora.pix.payment.infra.data.interfaces.IMongoContext
import kotlinx.coroutines.runBlocking
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