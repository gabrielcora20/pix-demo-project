package com.gabrielcora.presentation.payment.controllers

import com.gabrielcora.pix.payment.application.dto.ChangeRecurrenceDto
import com.gabrielcora.pix.payment.application.dto.RegisterPaymentDto
import com.gabrielcora.pix.payment.application.dto.UpdatePaymentDto
import com.gabrielcora.pix.payment.application.services.interfaces.IPaymentWriteAppService
import com.gabrielcora.pix.payment.domain.commands.results.DeletePaymentCommandResult
import com.gabrielcora.pix.payment.domain.commands.results.ChangeRecurrenceCommandResult
import com.gabrielcora.pix.payment.domain.commands.results.UpdatePaymentCommandResult
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/api/payment")
class PaymentWriteController @Autowired constructor(val paymentWriteAppService: IPaymentWriteAppService) {
    @Operation(summary = "Cadastra um pagamento", description = "Retorna 200 se o cadastro for bem sucedido")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Pagamento cadastrado"),
            ApiResponse(responseCode = "400", description = "Ocorreu um erro ao cadastrar o pagamento"),
        ]
    )
    @ResponseBody
    @PostMapping
    suspend fun post(@Valid @RequestBody req: RegisterPaymentDto): ResponseEntity<Any> {
        return ResponseEntity.ok(paymentWriteAppService.register(req))
    }

    @Operation(summary = "Atualiza um pagamento", description = "Retorna 200 se a atualização for bem sucedida")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Pagamento atualizado"),
            ApiResponse(responseCode = "404", description = "Nenhum pagamento encontrado com o id fornecido"),
            ApiResponse(responseCode = "400", description = "Ocorreu um erro ao atualizar o pagamento"),
        ]
    )
    @ResponseBody
    @PutMapping("/{id}")
    suspend fun put(@Valid @PathVariable id: String, @RequestBody req: UpdatePaymentDto): ResponseEntity<UpdatePaymentCommandResult> {
        return ResponseEntity.ok(paymentWriteAppService.update(id, req))
    }

    @Operation(summary = "Altera a recorrência de um pagamento", description = "Retorna 200 se a alteração da recorrência for bem sucedida")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Recorrência do pagamento alterada"),
            ApiResponse(responseCode = "404", description = "Nenhum pagamento encontrado com o id fornecido"),
            ApiResponse(responseCode = "400", description = "Ocorreu um erro ao alterar a recorrência do pagamento"),
        ]
    )
    @ResponseBody
    @PatchMapping("/change-recurrence/{id}")
    suspend fun changeRecurrence(@Valid @PathVariable id: String, @RequestBody req: ChangeRecurrenceDto): ResponseEntity<ChangeRecurrenceCommandResult> {
        return ResponseEntity.ok(paymentWriteAppService.changeRecurrence(id, req))
    }

    @Operation(summary = "Exclui um pagamento", description = "Retorna 200 se a exclusão for bem sucedida")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Pagamento excluído"),
            ApiResponse(responseCode = "404", description = "Nenhum pagamento encontrado com o id fornecido"),
            ApiResponse(responseCode = "400", description = "Ocorreu um erro ao excluir o pagamento"),
        ]
    )
    @ResponseBody
    @DeleteMapping("/{id}")
    suspend fun delete(@Valid @PathVariable id: String): ResponseEntity<DeletePaymentCommandResult> {
        return ResponseEntity.ok(paymentWriteAppService.delete(id))
    }
}