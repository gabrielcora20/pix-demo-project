package com.gabrielcora.pix.payment.domain.events.handlers

import com.gabrielcora.pix.payment.domain.events.PaymentDeletedEvent
import com.gabrielcora.pix.payment.domain.events.PaymentRegisteredEvent
import com.gabrielcora.pix.payment.domain.queries.GetAllPaymentsQuery
import com.gabrielcora.pix.payment.mocks.paymentEffectedMock
import com.gabrielcora.pix.payment.mocks.paymentsMock
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness
import org.springframework.amqp.rabbit.core.RabbitTemplate

@ExtendWith(MockitoExtension::class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PaymentRegisteredEventHandlerTest {
    @Test
    fun `test event handler`() {
        val rabbitTemplate = Mockito.mock<RabbitTemplate>()

        val eventHandler = PaymentRegisteredEventHandler(rabbitTemplate)

        runTest {
            eventHandler.handlePaymentRegisteredEvent(PaymentRegisteredEvent(paymentEffectedMock))
        }
    }
}