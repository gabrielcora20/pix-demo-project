package com.gabrielcora.pix.payment.infra.data.interfaces

interface IUnitOfWork {
    suspend fun commit(): Boolean
}