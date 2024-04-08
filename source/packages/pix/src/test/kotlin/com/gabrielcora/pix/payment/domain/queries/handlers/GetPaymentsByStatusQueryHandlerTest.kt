package com.gabrielcora.pix.payment.domain.queries.handlers

import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum
import com.gabrielcora.pix.payment.domain.queries.GetPaymentsByStatusQuery
import com.gabrielcora.pix.payment.infra.data.repository.read.PaymentReadRepository
import com.gabrielcora.pix.payment.mocks.paymentsCanceledMock
import com.gabrielcora.pix.payment.mocks.paymentsEffectedMock
import com.gabrielcora.pix.payment.mocks.paymentsScheduledMock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness

@ExtendWith(MockitoExtension::class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetPaymentsByStatusQueryHandlerTest {
    @Test
    fun `test query handler with status effected`() {
        val readRepository = Mockito.mock<PaymentReadRepository>()

        val query = GetPaymentsByStatusQuery(StatusEnum.EFFECTED)

        runTest {
            whenever(readRepository.findByStatus(StatusEnum.EFFECTED)).then{ paymentsEffectedMock.toList() }
        }

        val queryHandler = GetPaymentsByStatusQueryHandler(readRepository)

        runTest {
            val allPayments = queryHandler.handle(query)
            Assertions.assertEquals(paymentsEffectedMock.toList(), allPayments.toList())
        }
    }

    @Test
    fun `test query handler with status scheduled`() {
        val readRepository = Mockito.mock<PaymentReadRepository>()

        val query = GetPaymentsByStatusQuery(StatusEnum.SCHEDULED)

        runTest {
            whenever(readRepository.findByStatus(StatusEnum.SCHEDULED)).then{ paymentsScheduledMock.toList() }
        }

        val queryHandler = GetPaymentsByStatusQueryHandler(readRepository)

        runTest {
            val allPayments = queryHandler.handle(query)
            Assertions.assertEquals(paymentsScheduledMock.toList(), allPayments.toList())
        }
    }

    @Test
    fun `test query handler with status canceled`() {
        val readRepository = Mockito.mock<PaymentReadRepository>()

        val query = GetPaymentsByStatusQuery(StatusEnum.CANCELED)

        runTest {
            whenever(readRepository.findByStatus(StatusEnum.CANCELED)).then{ paymentsCanceledMock.toList() }
        }

        val queryHandler = GetPaymentsByStatusQueryHandler(readRepository)

        runTest {
            val allPayments = queryHandler.handle(query)
            Assertions.assertEquals(paymentsCanceledMock.toList(), allPayments.toList())
        }
    }
}