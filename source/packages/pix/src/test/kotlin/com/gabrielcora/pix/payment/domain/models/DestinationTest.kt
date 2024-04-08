package com.gabrielcora.pix.payment.domain.models

import com.gabrielcora.pix.payment.mocks.destinationMock
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness

@ExtendWith(MockitoExtension::class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DestinationTest {
    @Test
    fun `test payment model creation`() {
        Destination(
            destinationMock.pixKey,
            destinationMock.keyType)
    }
}