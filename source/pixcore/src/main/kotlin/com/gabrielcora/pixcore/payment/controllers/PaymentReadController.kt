package com.gabrielcora.pixcore.payment.controllers

import com.gabrielcora.pixcore.payment.application.dto.PaymentDTO
import com.gabrielcora.pixcore.payment.application.services.interfaces.IPaymentReadAppService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("payment")
class PaymentReadController @Autowired constructor(paymentReadAppService: IPaymentReadAppService) {
    private val _paymentReadAppService: IPaymentReadAppService = paymentReadAppService
    @ResponseBody
    @GetMapping
    fun get(): ResponseEntity<Iterable<PaymentDTO>> {
        this._paymentReadAppService.getAll()
        return ResponseEntity.ok(listOf(PaymentDTO("1", 1, Date(), Date(), 1.100, "Descrição Teste", "", "Joãozinho"), PaymentDTO("2", 1, Date(), Date(), 1.400, "Descrição Teste secundária", "", "Romário")))
    }
    @ResponseBody
    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<PaymentDTO> {
        return ResponseEntity.ok(PaymentDTO("1", 1, Date(), Date(), 1.100, "Descrição Teste", "", "Joãozinho"))
    }
}