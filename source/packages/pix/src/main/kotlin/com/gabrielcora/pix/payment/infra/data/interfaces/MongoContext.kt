package com.gabrielcora.pix.payment.infra.data.interfaces

import com.gabrielcora.pix.payment.domain.interfaces.UnitOfWork
import com.gabrielcora.pix.payment.domain.models.DomainEntity
import org.springframework.data.mongodb.core.MongoTemplate

interface MongoContext : AutoCloseable, UnitOfWork {
    val db: MongoTemplate
    fun addCommand(func: suspend () -> Unit, entity: DomainEntity)
    suspend fun saveChanges(): Int
}