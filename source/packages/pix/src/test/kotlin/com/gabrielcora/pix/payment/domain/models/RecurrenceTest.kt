package com.gabrielcora.pix.payment.domain.models

import com.gabrielcora.pix.payment.mocks.recurrenceMock
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness

@ExtendWith(MockitoExtension::class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RecurrenceTest {
    @Test
    fun `test recurrence model creation`() {
        Recurrence(
            recurrenceMock.endDate,
            recurrenceMock.frequencyType)
    }
}