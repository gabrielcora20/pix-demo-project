package com.gabrielcora.cqrspackage.infra.data.interfaces

interface IUnitOfWork {
    suspend fun commit(): Boolean
}