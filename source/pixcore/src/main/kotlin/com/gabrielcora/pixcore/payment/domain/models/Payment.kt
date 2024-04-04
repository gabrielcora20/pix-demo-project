package com.gabrielcora.pixcore.payment.domain.models

import com.gabrielcora.pixcore.payment.domain.commands.PartiallyUpdatePaymentCommand
import com.gabrielcora.pixcore.payment.domain.commands.RegisterNewPaymentCommand
import com.gabrielcora.pixcore.payment.domain.commands.UpdatePaymentCommand
import com.gabrielcora.cqrspackage.domain.events.Event
import com.gabrielcora.pixcore.payment.domain.events.PaymentPartiallyUpdatedEvent
import com.gabrielcora.pixcore.payment.domain.events.PaymentRegisteredEvent
import com.gabrielcora.pixcore.payment.domain.events.PaymentUpdatedEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.util.Assert
import java.util.*
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("payment")
class Payment(
    private var status: Int = 0,
    private var dataInclusao: Date = Date(),
    private var dataPagamento: Date = Date(),
    private var valorPagamento: Double = 0.0,
    private var descricaoPagamento: String = "",
    private var dadosRecorrencia: String = "",
    private var destinoPagamento: String = ""
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

    fun handle(command: PartiallyUpdatePaymentCommand): ObjectId {
        assert(command.descricaoPagamento.isNotBlank()) { "Descrição do pagamento não pode ser vazia!" }

        id = ObjectId(command.id)
        status = command.status
        dataInclusao = command.dataInclusao
        dataPagamento = command.dataPagamento
        valorPagamento = command.valorPagamento
        descricaoPagamento = command.descricaoPagamento
        dadosRecorrencia = command.dadosRecorrencia
        destinoPagamento = command.destinoPagamento

        raise(PaymentPartiallyUpdatedEvent(id!!))
        return id!!
    }
}