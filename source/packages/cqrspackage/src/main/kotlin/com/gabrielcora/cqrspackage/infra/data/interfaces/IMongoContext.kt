package com.gabrielcora.cqrspackage.infra.data.interfaces

import com.gabrielcora.cqrspackage.domain.models.DomainEntity
import org.springframework.data.mongodb.core.MongoTemplate

interface IMongoContext : AutoCloseable, IUnitOfWork {
    val db: MongoTemplate
    fun addCommand(func: suspend () -> Unit, entity: DomainEntity)
    suspend fun saveChanges(): Int
}