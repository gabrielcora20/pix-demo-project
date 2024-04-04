package com.gabrielcora.pixcore.payment.controllers

import com.gabrielcora.pixcore.payment.application.dto.RegisterPaymentDTO
import com.gabrielcora.pixcore.payment.application.dto.PartiallyUpdatePaymentDTO
import com.gabrielcora.pixcore.payment.application.dto.PaymentDTO
import com.gabrielcora.pixcore.payment.application.dto.UpdatePaymentDTO
import com.gabrielcora.pixcore.payment.application.services.interfaces.IPaymentWriteAppService
import com.gabrielcora.pixcore.payment.domain.commands.results.PartiallyUpdatePaymentCommandResult
import com.gabrielcora.pixcore.payment.domain.commands.results.RegisterNewPaymentCommandResult
import com.gabrielcora.pixcore.payment.domain.commands.results.UpdatePaymentCommandResult
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("payment")
class PaymentWriteController @Autowired constructor(paymentWriteAppService: IPaymentWriteAppService) {
    private val _paymentWriteAppService: IPaymentWriteAppService = paymentWriteAppService

    @Operation(summary = "Cadastra um pagamento", description = "Retorna 200 se o cadastro for bem sucedido")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Pagamento cadastrado"),
            ApiResponse(responseCode = "400", description = "Ocorreu um erro ao cadastrar o pagamento"),
        ]
    )
    @ResponseBody
    @PostMapping
    suspend fun post(@RequestBody req: RegisterPaymentDTO): ResponseEntity<RegisterNewPaymentCommandResult> {
        return ResponseEntity.ok(_paymentWriteAppService.register(req))
    }

    @ResponseBody
    @PutMapping("/{id}")
    suspend fun put(@PathVariable id: String, @RequestBody req: UpdatePaymentDTO): ResponseEntity<UpdatePaymentCommandResult> {
        return ResponseEntity.ok(_paymentWriteAppService.update(id, req))
    }

    @ResponseBody
    @PatchMapping("/{id}")
    suspend fun patch(@PathVariable id: String, @RequestBody req: PartiallyUpdatePaymentDTO): ResponseEntity<PartiallyUpdatePaymentCommandResult> {
        return ResponseEntity.ok(_paymentWriteAppService.partiallyUpdate(id, req))
    }
}