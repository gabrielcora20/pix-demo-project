package com.gabrielcora.pix.payment.domain.interfaces

interface IUnitOfWork {
    suspend fun commit(): Boolean
}