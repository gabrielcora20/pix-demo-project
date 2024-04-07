package com.gabrielcora.pix.payment.infra.data.repository.write

import com.gabrielcora.pix.payment.domain.interfaces.UnitOfWork
import com.gabrielcora.pix.payment.domain.interfaces.repository.write.PaymentWriteRepository
import com.gabrielcora.pix.payment.domain.models.Payment
import com.gabrielcora.pix.payment.infra.data.interfaces.MongoContext
import kotlinx.coroutines.runBlocking
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository

@Repository
class PaymentWriteRepository(private val context: MongoContext) :
    PaymentWriteRepository {
    protected val dbSet: MongoTemplate = context.db
    override val unitOfWork: UnitOfWork = context

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