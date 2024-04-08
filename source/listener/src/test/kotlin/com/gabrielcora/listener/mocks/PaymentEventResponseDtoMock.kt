package com.gabrielcora.listener.mocks

import com.gabrielcora.listener.dto.DestinationEventResponseDto
import com.gabrielcora.listener.dto.PaymentEventResponseDto
import com.gabrielcora.listener.dto.RecurrenceEventResponseDto
import com.gabrielcora.pix.payment.domain.models.enums.FrequencyTypeEnum
import com.gabrielcora.pix.payment.domain.models.enums.PixKeyTypeEnum
import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.time.LocalDateTime

val paymentEventResponseDtoMock: PaymentEventResponseDto = mock(PaymentEventResponseDto::class.java).apply {
    `when`(id).thenReturn("6611f74a927c68691c418622")
    `when`(status).thenReturn(StatusEnum.EFFECTED)
    `when`(inclusionDate).thenReturn(LocalDateTime.now())
    `when`(paymentDate).thenReturn(LocalDateTime.now())
    `when`(value).thenReturn(100.0)
    `when`(description).thenReturn("Payment description")
    `when`(recurrence).thenReturn(recurrenceEventResponseDtoMock)
    `when`(destination).thenReturn(destinationEventResponseDtoMock)
}

val destinationEventResponseDtoMock: DestinationEventResponseDto = mock(DestinationEventResponseDto::class.java).apply {
    `when`(pixKey).thenReturn("gabrielcora20@gmail.com")
    `when`(keyType).thenReturn(PixKeyTypeEnum.EMAIL)
}

val recurrenceEventResponseDtoMock: RecurrenceEventResponseDto = mock(RecurrenceEventResponseDto::class.java).apply {
    `when`(endDate).thenReturn(LocalDateTime.now())
    `when`(frequencyType).thenReturn(FrequencyTypeEnum.MONTHLY)
}