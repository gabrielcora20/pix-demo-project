package com.gabrielcora.presentation.payment.controllers

import com.gabrielcora.pix.payment.application.dto.PatchPaymentDTO
import com.gabrielcora.pix.payment.application.dto.RegisterPaymentDTO
import com.gabrielcora.pix.payment.application.dto.UpdatePaymentDTO
import com.gabrielcora.pix.payment.application.services.interfaces.IPaymentWriteAppService
import com.gabrielcora.pix.payment.domain.commands.results.DeletePaymentCommandResult
import com.gabrielcora.pix.payment.domain.commands.results.PatchPaymentCommandResult
import com.gabrielcora.pix.payment.domain.commands.results.RegisterNewPaymentCommandResult
import com.gabrielcora.pix.payment.domain.commands.results.UpdatePaymentCommandResult
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

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
    suspend fun post(@Valid @RequestBody req: RegisterPaymentDTO): ResponseEntity<Any> {
        return ResponseEntity.ok(_paymentWriteAppService.register(req))
    }

    @ResponseBody
    @PutMapping("/{id}")
    suspend fun put(@Valid @PathVariable id: String, @RequestBody req: UpdatePaymentDTO): ResponseEntity<UpdatePaymentCommandResult> {
        return ResponseEntity.ok(_paymentWriteAppService.update(id, req))
    }

    @ResponseBody
    @PatchMapping("/{id}")
    suspend fun patch(@Valid @PathVariable id: String, @RequestBody req: PatchPaymentDTO): ResponseEntity<PatchPaymentCommandResult> {
        return ResponseEntity.ok(_paymentWriteAppService.patch(id, req))
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    suspend fun delete(@Valid @PathVariable id: String): ResponseEntity<DeletePaymentCommandResult> {
        return ResponseEntity.ok(_paymentWriteAppService.delete(id))
    }
}