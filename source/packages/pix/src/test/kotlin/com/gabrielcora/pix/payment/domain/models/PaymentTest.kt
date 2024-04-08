package com.gabrielcora.pix.payment.domain.models

import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum
import com.gabrielcora.pix.payment.mocks.paymentEffectedMock
import com.gabrielcora.pix.payment.mocks.paymentScheduledMock
import com.gabrielcora.pix.payment.mocks.paymentsEffectedMock
import com.gabrielcora.pix.payment.mocks.paymentsScheduledMock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PaymentTest {
    @Test
    fun `test payment model creation`() {
        Payment(
            paymentEffectedMock.paymentDate,
            paymentEffectedMock.value,
            paymentEffectedMock.description,
            paymentEffectedMock.recurrence,
            paymentEffectedMock.destination,
            paymentEffectedMock.status,
            paymentEffectedMock.inclusionDate)
    }

    @Test
    fun `test getStatusByPaymentDate method with effected payment data`() {
        val payment = Payment(
            paymentEffectedMock.paymentDate,
            paymentEffectedMock.value,
            paymentEffectedMock.description,
            paymentEffectedMock.recurrence,
            paymentEffectedMock.destination,
            paymentEffectedMock.status,
            paymentEffectedMock.inclusionDate)

        val status = payment.getStatusByPaymentDate(LocalDateTime.now())

        assertThat(status).isEqualTo(StatusEnum.EFFECTED)
    }

    @Test
    fun `test getStatusByPaymentDate method with scheduled payment data`() {
        val payment = Payment(
            paymentScheduledMock.paymentDate,
            paymentScheduledMock.value,
            paymentScheduledMock.description,
            paymentScheduledMock.recurrence,
            paymentScheduledMock.destination,
            paymentScheduledMock.status,
            paymentScheduledMock.inclusionDate)

        val status = payment.getStatusByPaymentDate(LocalDateTime.now())

        assertThat(status).isEqualTo(StatusEnum.SCHEDULED)
    }
}