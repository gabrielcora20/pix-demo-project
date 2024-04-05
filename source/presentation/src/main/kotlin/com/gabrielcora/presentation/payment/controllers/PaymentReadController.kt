package com.gabrielcora.presentation.payment.controllers

import com.gabrielcora.pix.payment.application.services.interfaces.IPaymentReadAppService
import com.gabrielcora.pix.payment.domain.models.Payment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("payment")
class PaymentReadController @Autowired constructor(private val paymentReadAppService: IPaymentReadAppService) {
    @ResponseBody
    @GetMapping
    suspend fun getAll(): ResponseEntity<Iterable<Payment?>> {
        return ResponseEntity.ok(this.paymentReadAppService.getAll())
    }

    @ResponseBody
    @GetMapping("effect")
    suspend fun getEffectedPayments(): ResponseEntity<Iterable<Payment?>> {
        return ResponseEntity.ok(this.paymentReadAppService.getEffected())
    }

    @ResponseBody
    @GetMapping("scheduled")
    suspend fun getScheduledPayments(): ResponseEntity<Iterable<Payment?>> {
        return ResponseEntity.ok(this.paymentReadAppService.getScheduled())
    }
}