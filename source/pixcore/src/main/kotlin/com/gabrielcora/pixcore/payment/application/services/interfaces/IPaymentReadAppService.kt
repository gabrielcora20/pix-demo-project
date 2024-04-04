package com.gabrielcora.pixcore.payment.application.services.interfaces

interface IPaymentReadAppService {
    fun getAll()
    fun getById(id: String)
}