package com.gabrielcora.pix.payment.domain.commands.handlers

import com.gabrielcora.pix.payment.domain.commands.DeletePaymentCommand
import com.gabrielcora.pix.payment.domain.exceptions.PaymentNotFoundException
import com.gabrielcora.pix.payment.domain.interfaces.UnitOfWork
import com.gabrielcora.pix.payment.infra.data.repository.read.PaymentReadRepository
import com.gabrielcora.pix.payment.infra.data.repository.write.PaymentWriteRepository
import com.gabrielcora.pix.payment.mocks.paymentCanceledMock
import com.gabrielcora.pix.payment.mocks.paymentEffectedMock
import com.gabrielcora.pix.payment.mocks.recurrenceMock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runTest
import org.apache.coyote.BadRequestException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness

@ExtendWith(MockitoExtension::class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DeletePaymentCommandHandlerTest {
    @Test
    fun `test command handler`() {
        val readRepository = mock<PaymentReadRepository>()
        val writeRepository = mock<PaymentWriteRepository>()

        runTest {
            val unitOfWork = mock<UnitOfWork>()

            whenever(unitOfWork.commit()).then { true }

            whenever(writeRepository.unitOfWork).then { unitOfWork }
        }

        val command = DeletePaymentCommand(paymentCanceledMock.id.toString())

        val commandHandler = DeletePaymentCommandHandler(writeRepository, readRepository)

        runTest {
            whenever(readRepository.findById(paymentCanceledMock.id.toString())).then{ paymentCanceledMock }

            val commandResult = commandHandler.handle(command)
            Assertions.assertEquals(commandResult.deletedPayment, paymentCanceledMock)
        }
    }

    @Test
    fun `test command handler with exception on commit changes`() {
        val readRepository = mock<PaymentReadRepository>()
        val writeRepository = mock<PaymentWriteRepository>()

        runTest {
            val unitOfWork = mock<UnitOfWork>()

            whenever(unitOfWork.commit()).then { false }

            whenever(writeRepository.unitOfWork).then { unitOfWork }
        }

        val command = DeletePaymentCommand(paymentCanceledMock.id.toString())

        val commandHandler = DeletePaymentCommandHandler(writeRepository, readRepository)

        runTest {
            whenever(readRepository.findById(paymentCanceledMock.id.toString())).then{ paymentCanceledMock }

            assertThrows<BadRequestException> {commandHandler.handle(command)}
        }
    }

    @Test
    fun `test command handler with exception on find payment by id`() {
        val readRepository = mock<PaymentReadRepository>()
        val writeRepository = mock<PaymentWriteRepository>()

        runTest {
            val unitOfWork = mock<UnitOfWork>()

            whenever(unitOfWork.commit()).then { true }

            whenever(writeRepository.unitOfWork).then { unitOfWork }
        }

        val command = DeletePaymentCommand(paymentCanceledMock.id.toString())

        val commandHandler = DeletePaymentCommandHandler(writeRepository, readRepository)

        runTest {
            whenever(readRepository.findById(paymentCanceledMock.id.toString())).then{ null }

            assertThrows<PaymentNotFoundException> {commandHandler.handle(command)}
        }
    }
}