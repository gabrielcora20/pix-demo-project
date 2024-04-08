package com.gabrielcora.pix.payment.domain.exceptions

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness

@ExtendWith(MockitoExtension::class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PaymentNotFoundExceptionTest {
    @Test
    fun `test PaymentNotFound Exception creation`() {
        PaymentNotFoundException("message")
    }
}