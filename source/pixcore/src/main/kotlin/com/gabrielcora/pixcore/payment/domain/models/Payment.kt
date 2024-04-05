package com.gabrielcora.pixcore.payment.domain.models

import com.gabrielcora.pixcore.payment.domain.commands.PatchPaymentCommand
import com.gabrielcora.pixcore.payment.domain.commands.RegisterNewPaymentCommand
import com.gabrielcora.pixcore.payment.domain.commands.UpdatePaymentCommand
import com.gabrielcora.pixcore.payment.domain.events.PaymentPatchedEvent
import com.gabrielcora.pixcore.payment.domain.events.PaymentRegisteredEvent
import com.gabrielcora.pixcore.payment.domain.events.PaymentUpdatedEvent
import java.util.*
import org.bson.types.ObjectId
import org.springframework.beans.BeanUtils
import org.springframework.data.mongodb.core.mapping.Document

@Document("payment")
class Payment(
    var status: Int = 0,
    var dataInclusao: Date = Date(),
    var dataPagamento: Date = Date(),
    var valorPagamento: Double = 0.0,
    var descricaoPagamento: String = "",
    var dadosRecorrencia: String = "",
    var destinoPagamento: String = ""
) : com.gabrielcora.cqrspackage.domain.models.DomainEntity() {

    fun handle(command: RegisterNewPaymentCommand): ObjectId {
        assert(command.descricaoPagamento.isNotBlank()) { "Descrição do pagamento não pode ser vazia!" }

        id = ObjectId()
        status = command.status
        dataInclusao = command.dataInclusao
        dataPagamento = command.dataPagamento
        valorPagamento = command.valorPagamento
        descricaoPagamento = command.descricaoPagamento
        dadosRecorrencia = command.dadosRecorrencia
        destinoPagamento = command.destinoPagamento

        raise(PaymentRegisteredEvent(id!!))
        return id!!
    }

    fun handle(command: UpdatePaymentCommand): ObjectId {
        assert(command.descricaoPagamento.isNotBlank()) { "Descrição do pagamento não pode ser vazia!" }

        id = ObjectId(command.id)
        status = command.status
        dataInclusao = command.dataInclusao
        dataPagamento = command.dataPagamento
        valorPagamento = command.valorPagamento
        descricaoPagamento = command.descricaoPagamento
        dadosRecorrencia = command.dadosRecorrencia
        destinoPagamento = command.destinoPagamento

        raise(PaymentUpdatedEvent(id!!))
        return id!!
    }

    fun handle(command: PatchPaymentCommand): ObjectId {
        for ((key, value) in command.payment) {
            if (BeanUtils.getPropertyDescriptor(this::class.java, key) != null) {
                try {
                    val field = this::class.java.getDeclaredField(key)
                    field.isAccessible = true
                    field.set(this, value)
                } catch (e: NoSuchFieldException) {}
            }
        }

        raise(PaymentPatchedEvent(id!!))
        return id!!
    }
}