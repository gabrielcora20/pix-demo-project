package com.gabrielcora.listener.eventlisteners

import com.gabrielcora.listener.mock.paymentEventResponseDtoMock
import nl.altindag.log.LogCaptor
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness

@ExtendWith(MockitoExtension::class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PaymentChangedEventListenerTests {
    @Test
    fun `handlePaymentRecurrenceChanged should log the execution`() {
        val logCaptor = LogCaptor.forClass(PaymentChangedEventListener::class.java)
        PaymentChangedEventListener().handlePaymentRecurrenceChanged(paymentEventResponseDtoMock)

        val logEvents = logCaptor.logEvents
        assertThat(logEvents).hasSize(1)

        assertThat(logEvents[0].message).isEqualTo("executing handlePaymentRecurrenceChanged")
    }

    @Test
    fun `handlePaymentDeleted should log the execution`() {
        val logCaptor = LogCaptor.forClass(PaymentChangedEventListener::class.java)
        PaymentChangedEventListener().handlePaymentDeleted(paymentEventResponseDtoMock)

        val logEvents = logCaptor.logEvents
        assertThat(logEvents).hasSize(1)

        assertThat(logEvents[0].message).isEqualTo("executing handlePaymentDeleted")
    }

    @Test
    fun `handlePaymentUpdated should log the execution`() {
        val logCaptor = LogCaptor.forClass(PaymentChangedEventListener::class.java)
        PaymentChangedEventListener().handlePaymentUpdated(paymentEventResponseDtoMock)

        val logEvents = logCaptor.logEvents
        assertThat(logEvents).hasSize(1)

        assertThat(logEvents[0].message).isEqualTo("executing handlePaymentUpdated")
    }

    @Test
    fun `handlePaymentRegistered should log the execution`() {
        val logCaptor = LogCaptor.forClass(PaymentChangedEventListener::class.java)
        PaymentChangedEventListener().handlePaymentRegistered(paymentEventResponseDtoMock)

        val logEvents = logCaptor.logEvents
        assertThat(logEvents).hasSize(1)

        assertThat(logEvents[0].message).isEqualTo("executing handlePaymentRegistered")
    }
}