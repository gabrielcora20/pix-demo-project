package com.gabrielcora.pix.payment.infra.data.interfaces

import com.gabrielcora.pix.payment.domain.models.DomainEntity
import org.springframework.data.mongodb.core.MongoTemplate

interface IMongoContext : AutoCloseable, IUnitOfWork {
    val db: MongoTemplate
    fun addCommand(func: suspend () -> Unit, entity: DomainEntity)
    suspend fun saveChanges(): Int
}