package com.gabrielcora.presentation.payment.controllers

import com.gabrielcora.pix.payment.application.services.interfaces.IPaymentReadAppService
import com.gabrielcora.pix.payment.domain.models.Payment
import io.opentelemetry.api.GlobalOpenTelemetry
import io.opentelemetry.api.trace.Tracer
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/api/payment")
class PaymentReadController @Autowired constructor(private val paymentReadAppService: IPaymentReadAppService) {
    private val log: Logger = LoggerFactory.getLogger(PaymentReadController::class.java)
    @Operation(summary = "Retorna todos os pagamentos", description = "Retorna todos os pagamentos cadastrados")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Pagamentos encontrados"),
            ApiResponse(responseCode = "404", description = "Nenhum pagamento encontrado"),
            ApiResponse(responseCode = "400", description = "Ocorreu um erro ao buscar os pagamentos"),
        ]
    )
    @ResponseBody
    @GetMapping
    suspend fun getAll(): ResponseEntity<Iterable<Payment?>> {
        return ResponseEntity.ok(this.paymentReadAppService.getAll())
    }

    @Operation(summary = "Retorna pagamentos realizados", description = "Retorna todos os pagamentos realizados")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Pagamentos realizados encontrados"),
            ApiResponse(responseCode = "404", description = "Nenhum pagamento realizado encontrado"),
            ApiResponse(responseCode = "400", description = "Ocorreu um erro ao buscar os pagamentos realizados"),
        ]
    )
    @ResponseBody
    @GetMapping("effected")
    suspend fun getEffectedPayments(): ResponseEntity<Iterable<Payment?>> {
        return ResponseEntity.ok(this.paymentReadAppService.getEffected())
    }

    @Operation(summary = "Retorna pagamentos agendados", description = "Retorna todos os pagamentos agendados")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Pagamentos agendados encontrados"),
            ApiResponse(responseCode = "404", description = "Nenhum pagamento agendado encontrado"),
            ApiResponse(responseCode = "400", description = "Ocorreu um erro ao buscar os pagamentos agendados"),
        ]
    )
    @ResponseBody
    @GetMapping("scheduled")
    suspend fun getScheduledPayments(): ResponseEntity<Iterable<Payment?>> {
        return ResponseEntity.ok(this.paymentReadAppService.getScheduled())
    }
}