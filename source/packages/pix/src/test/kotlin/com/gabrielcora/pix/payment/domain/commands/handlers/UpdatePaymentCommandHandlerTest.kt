package com.gabrielcora.pix.payment.domain.commands.handlers

import com.gabrielcora.pix.payment.domain.commands.UpdatePaymentCommand
import com.gabrielcora.pix.payment.domain.exceptions.PaymentNotFoundException
import com.gabrielcora.pix.payment.domain.interfaces.UnitOfWork
import com.gabrielcora.pix.payment.domain.models.Payment
import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum
import com.gabrielcora.pix.payment.infra.crosscutting.helpers.interfaces.PixKeyHelper
import com.gabrielcora.pix.payment.infra.data.repository.read.PaymentReadRepository
import com.gabrielcora.pix.payment.infra.data.repository.write.PaymentWriteRepository
import com.gabrielcora.pix.payment.mocks.*
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runTest
import org.apache.coyote.BadRequestException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UpdatePaymentCommandHandlerTest {
    @Test
    fun `test command handler`() {
        runTest {
            val readRepository = mock<PaymentReadRepository>()
            val writeRepository = mock<PaymentWriteRepository>()
            val pixKeyHelper = mock<PixKeyHelper>()

            val currentDate = LocalDateTime.now()

            mockStatic(LocalDateTime::class.java, CALLS_REAL_METHODS).use {
                `when`(LocalDateTime.now()).then{currentDate}
            }

            `when`(pixKeyHelper.getTypeFromPixKey(destinationMock.pixKey)).then { destinationMock.keyType }
            `when`(paymentScheduledMock.getStatusByPaymentDate(currentDate)).then { StatusEnum.SCHEDULED }


            val unitOfWork = mock<UnitOfWork>()

            whenever(unitOfWork.commit()).then { true }

            whenever(writeRepository.unitOfWork).then { unitOfWork }

            val command = UpdatePaymentCommand(
                paymentScheduledMock.id.toString(),
                paymentScheduledMock.paymentDate,
                paymentScheduledMock.value,
                paymentScheduledMock.description,
                paymentScheduledMock.recurrence,
                destinationMock.pixKey)

            val commandHandler = UpdatePaymentCommandHandler(writeRepository, readRepository, pixKeyHelper)

            whenever(readRepository.findById(paymentScheduledMock.id.toString())).then{ paymentScheduledMock }
            whenever(readRepository.findSimilarPayments(anyOrNull())).then{ listOf<Payment>() }

            val commandResult = commandHandler.handle(command)
            Assertions.assertEquals(commandResult.updatedPayment, paymentScheduledMock)
            Assertions.assertNull(commandResult.warnings)
        }
    }

    @Test
    fun `test command handler with null payment date`() {
        val readRepository = mock<PaymentReadRepository>()
        val writeRepository = mock<PaymentWriteRepository>()
        val pixKeyHelper = mock<PixKeyHelper>()

        val currentDate = LocalDateTime.now()

        mockStatic(LocalDateTime::class.java, CALLS_REAL_METHODS).use {
            `when`(LocalDateTime.now()).then{currentDate}
        }

        `when`(pixKeyHelper.getTypeFromPixKey(destinationMock.pixKey)).then { destinationMock.keyType }
        `when`(paymentScheduledMock.getStatusByPaymentDate(currentDate)).then { StatusEnum.SCHEDULED }

        runTest {
            val unitOfWork = mock<UnitOfWork>()

            whenever(unitOfWork.commit()).then { true }

            whenever(writeRepository.unitOfWork).then { unitOfWork }
        }

        val command = UpdatePaymentCommand(
            paymentScheduledMock.id.toString(),
            null,
            paymentScheduledMock.value,
            paymentScheduledMock.description,
            paymentScheduledMock.recurrence,
            destinationMock.pixKey)

        val commandHandler = UpdatePaymentCommandHandler(writeRepository, readRepository, pixKeyHelper)

        runTest {
            whenever(readRepository.findById(paymentScheduledMock.id.toString())).then{ paymentScheduledMock }
            whenever(readRepository.findSimilarPayments(anyOrNull())).then{ listOf<Payment>() }

            val commandResult = commandHandler.handle(command)
            Assertions.assertEquals(commandResult.updatedPayment, paymentScheduledMock)
            Assertions.assertNull(commandResult.warnings)
        }
    }

    @Test
    fun `test command handler with similar payments found warning`() {
        val readRepository = mock<PaymentReadRepository>()
        val writeRepository = mock<PaymentWriteRepository>()
        val pixKeyHelper = mock<PixKeyHelper>()

        val currentDate = LocalDateTime.now()

        mockStatic(LocalDateTime::class.java, CALLS_REAL_METHODS).use {
            `when`(LocalDateTime.now()).then{currentDate}
        }

        `when`(pixKeyHelper.getTypeFromPixKey(destinationMock.pixKey)).then { destinationMock.keyType }
        `when`(paymentScheduledMock.getStatusByPaymentDate(currentDate)).then { StatusEnum.SCHEDULED }

        runTest {
            val unitOfWork = mock<UnitOfWork>()

            whenever(unitOfWork.commit()).then { true }

            whenever(writeRepository.unitOfWork).then { unitOfWork }
        }

        val command = UpdatePaymentCommand(
            paymentScheduledMock.id.toString(),
            paymentScheduledMock.paymentDate,
            paymentScheduledMock.value,
            paymentScheduledMock.description,
            paymentScheduledMock.recurrence,
            destinationMock.pixKey)

        val commandHandler = UpdatePaymentCommandHandler(writeRepository, readRepository, pixKeyHelper)

        runTest {
            whenever(readRepository.findById(paymentScheduledMock.id.toString())).then{ paymentScheduledMock }
            whenever(readRepository.findSimilarPayments(anyOrNull())).then{ paymentsScheduledMock }

            val commandResult = commandHandler.handle(command)
            Assertions.assertEquals(commandResult.updatedPayment, paymentScheduledMock)
            Assertions.assertNotNull(commandResult.warnings)
        }
    }

    @Test
    fun `test command handler with exception on commit changes`() {
        val readRepository = mock<PaymentReadRepository>()
        val writeRepository = mock<PaymentWriteRepository>()
        val pixKeyHelper = mock<PixKeyHelper>()

        val currentDate = LocalDateTime.now()

        mockStatic(LocalDateTime::class.java, CALLS_REAL_METHODS).use {
            `when`(LocalDateTime.now()).then{currentDate}
        }

        `when`(pixKeyHelper.getTypeFromPixKey(destinationMock.pixKey)).then { destinationMock.keyType }
        `when`(paymentScheduledMock.getStatusByPaymentDate(currentDate)).then { StatusEnum.SCHEDULED }

        runTest {
            val unitOfWork = mock<UnitOfWork>()

            whenever(unitOfWork.commit()).then { false }

            whenever(writeRepository.unitOfWork).then { unitOfWork }
        }

        val command = UpdatePaymentCommand(
            paymentScheduledMock.id.toString(),
            paymentScheduledMock.paymentDate,
            paymentScheduledMock.value,
            paymentScheduledMock.description,
            paymentScheduledMock.recurrence,
            destinationMock.pixKey)

        val commandHandler = UpdatePaymentCommandHandler(writeRepository, readRepository, pixKeyHelper)

        runTest {
            whenever(readRepository.findById(paymentScheduledMock.id.toString())).then{ paymentScheduledMock }
            whenever(readRepository.findSimilarPayments(anyOrNull())).then{ listOf<Payment>() }

            assertThrows<BadRequestException> {commandHandler.handle(command)}
        }
    }

    @Test
    fun `test command handler with exception on find payment by id`() {
        val readRepository = mock<PaymentReadRepository>()
        val writeRepository = mock<PaymentWriteRepository>()
        val pixKeyHelper = mock<PixKeyHelper>()

        runTest {
            val unitOfWork = mock<UnitOfWork>()

            whenever(unitOfWork.commit()).then { true }

            whenever(writeRepository.unitOfWork).then { unitOfWork }
        }

        val command = UpdatePaymentCommand(
            paymentScheduledMock.id.toString(),
            paymentScheduledMock.paymentDate,
            paymentScheduledMock.value,
            paymentScheduledMock.description,
            paymentScheduledMock.recurrence,
            destinationMock.pixKey)

        val commandHandler = UpdatePaymentCommandHandler(writeRepository, readRepository, pixKeyHelper)

        runTest {
            whenever(readRepository.findById(paymentScheduledMock.id.toString())).then{ null }

            assertThrows<PaymentNotFoundException> {commandHandler.handle(command)}
        }
    }

    @Test
    fun `test command handler with exception on pix key cast`() {
        val readRepository = mock<PaymentReadRepository>()
        val writeRepository = mock<PaymentWriteRepository>()
        val pixKeyHelper = mock<PixKeyHelper>()

        `when`(pixKeyHelper.getTypeFromPixKey(destinationMock.pixKey)).thenThrow(IllegalArgumentException("A chave pix fornecida é inválida"))

        val currentDate = LocalDateTime.now()

        mockStatic(LocalDateTime::class.java, CALLS_REAL_METHODS).use {
            `when`(LocalDateTime.now()).then{currentDate}
        }

        `when`(paymentScheduledMock.getStatusByPaymentDate(currentDate)).then { StatusEnum.SCHEDULED }

        runTest {
            val unitOfWork = mock<UnitOfWork>()

            whenever(unitOfWork.commit()).then { false }

            whenever(writeRepository.unitOfWork).then { unitOfWork }
        }

        val command = UpdatePaymentCommand(
            paymentScheduledMock.id.toString(),
            paymentScheduledMock.paymentDate,
            paymentScheduledMock.value,
            paymentScheduledMock.description,
            paymentScheduledMock.recurrence,
            destinationMock.pixKey)

        val commandHandler = UpdatePaymentCommandHandler(writeRepository, readRepository, pixKeyHelper)

        runTest {
            whenever(readRepository.findById(paymentScheduledMock.id.toString())).then{ paymentScheduledMock }
            whenever(readRepository.findSimilarPayments(anyOrNull())).then{ listOf<Payment>() }

            assertThrows<IllegalArgumentException> {commandHandler.handle(command)}
        }
    }
}