package com.gabrielcora.pix.payment.domain.queries.handlers

import com.gabrielcora.pix.payment.domain.queries.GetAllPaymentsQuery
import com.gabrielcora.pix.payment.infra.data.repository.read.PaymentReadRepository
import com.gabrielcora.pix.payment.mocks.paymentsMock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness
import kotlinx.coroutines.test.runTest

@ExtendWith(MockitoExtension::class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetAllPaymentsQueryHandlerTest {
    @Test
    fun `test query handler`() {
        runTest {
            val readRepository = mock<PaymentReadRepository>()

            whenever(readRepository.findAll()).then{ paymentsMock.toList() }

            val queryHandler = GetAllPaymentsQueryHandler(readRepository)

            val allPayments = queryHandler.handle(GetAllPaymentsQuery())
            Assertions.assertEquals(paymentsMock.toList(), allPayments.toList())
        }
    }
}