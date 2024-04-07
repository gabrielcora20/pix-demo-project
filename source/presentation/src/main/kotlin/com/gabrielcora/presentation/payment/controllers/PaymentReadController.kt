package com.gabrielcora.presentation.payment.controllers

import com.gabrielcora.pix.payment.application.services.interfaces.IPaymentReadAppService
import com.gabrielcora.pix.payment.domain.models.Payment
import io.opentelemetry.api.GlobalOpenTelemetry
import io.opentelemetry.api.trace.Tracer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/api/payment")
class PaymentReadController @Autowired constructor(private val paymentReadAppService: IPaymentReadAppService) {
    private val log: Logger = LoggerFactory.getLogger(PaymentReadController::class.java)
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