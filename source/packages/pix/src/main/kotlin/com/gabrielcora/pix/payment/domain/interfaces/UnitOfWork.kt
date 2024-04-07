package com.gabrielcora.pix.payment.domain.interfaces

interface UnitOfWork {
    suspend fun commit(): Boolean
}