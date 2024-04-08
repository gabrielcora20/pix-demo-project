package com.gabrielcora.pix.payment.mocks

import com.gabrielcora.pix.payment.domain.models.Destination
import com.gabrielcora.pix.payment.domain.models.Payment
import com.gabrielcora.pix.payment.domain.models.Recurrence
import com.gabrielcora.pix.payment.domain.models.enums.FrequencyTypeEnum
import com.gabrielcora.pix.payment.domain.models.enums.PixKeyTypeEnum
import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum
import org.bson.types.ObjectId
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.time.LocalDateTime

val paymentEffectedMock: Payment = mock(Payment::class.java).apply {
    `when`(id).thenReturn(ObjectId("6611f74a927c68691c418622"))
    `when`(status).thenReturn(StatusEnum.EFFECTED)
    `when`(inclusionDate).thenReturn(LocalDateTime.now())
    `when`(paymentDate).thenReturn(LocalDateTime.now())
    `when`(value).thenReturn(100.0)
    `when`(description).thenReturn("Payment description")
    `when`(recurrence).thenReturn(recurrenceMock)
    `when`(destination).thenReturn(destinationMock)
}

val paymentScheduledMock: Payment = mock<Payment>().apply {
    `when`(id).thenReturn(ObjectId("6611f74a927c68691c418624"))
    `when`(status).thenReturn(StatusEnum.SCHEDULED)
    `when`(inclusionDate).thenReturn(LocalDateTime.now())
    `when`(paymentDate).thenReturn(LocalDateTime.now().plusDays(3))
    `when`(value).thenReturn(200.0)
    `when`(description).thenReturn("Scheduled payment description")
    `when`(recurrence).thenReturn(recurrenceMock)
    `when`(destination).thenReturn(destinationMock)
}

val paymentCanceledMock: Payment = mock<Payment>().apply {
    `when`(id).thenReturn(ObjectId("6611f74a927c68691c418626"))
    `when`(status).thenReturn(StatusEnum.CANCELED)
    `when`(inclusionDate).thenReturn(LocalDateTime.now())
    `when`(paymentDate).thenReturn(LocalDateTime.now())
    `when`(value).thenReturn(300.0)
    `when`(description).thenReturn("Canceled payment description")
    `when`(recurrence).thenReturn(recurrenceMock)
    `when`(destination).thenReturn(destinationMock)
}


val paymentsEffectedMock: List<Payment> = listOf(
    mock<Payment>().apply {
        `when`(id).thenReturn(ObjectId("6611f74a927c68691c418622"))
        `when`(status).thenReturn(StatusEnum.EFFECTED)
        `when`(inclusionDate).thenReturn(LocalDateTime.now())
        `when`(paymentDate).thenReturn(LocalDateTime.now())
        `when`(value).thenReturn(100.0)
        `when`(description).thenReturn("Payment description")
        `when`(recurrence).thenReturn(recurrenceMock)
        `when`(destination).thenReturn(destinationMock)
    },
    mock<Payment>().apply {
        `when`(id).thenReturn(ObjectId("6611f74a927c68691c418623"))
        `when`(status).thenReturn(StatusEnum.EFFECTED)
        `when`(inclusionDate).thenReturn(LocalDateTime.now())
        `when`(paymentDate).thenReturn(LocalDateTime.now())
        `when`(value).thenReturn(150.0)
        `when`(description).thenReturn("Another payment description")
        `when`(recurrence).thenReturn(recurrenceMock)
        `when`(destination).thenReturn(destinationMock)
    }
)

val paymentsScheduledMock: List<Payment> = listOf(
    mock<Payment>().apply {
        `when`(id).thenReturn(ObjectId("6611f74a927c68691c418624"))
        `when`(status).thenReturn(StatusEnum.SCHEDULED)
        `when`(inclusionDate).thenReturn(LocalDateTime.now())
        `when`(paymentDate).thenReturn(LocalDateTime.now().plusDays(3))
        `when`(value).thenReturn(200.0)
        `when`(description).thenReturn("Scheduled payment description")
        `when`(recurrence).thenReturn(recurrenceMock)
        `when`(destination).thenReturn(destinationMock)
    },
    mock<Payment>().apply {
        `when`(id).thenReturn(ObjectId("6611f74a927c68691c418625"))
        `when`(status).thenReturn(StatusEnum.SCHEDULED)
        `when`(inclusionDate).thenReturn(LocalDateTime.now())
        `when`(paymentDate).thenReturn(LocalDateTime.now().plusDays(5))
        `when`(value).thenReturn(250.0)
        `when`(description).thenReturn("Another scheduled payment description")
        `when`(recurrence).thenReturn(recurrenceMock)
        `when`(destination).thenReturn(destinationMock)
    }
)

val paymentsCanceledMock: List<Payment> = listOf(
    mock<Payment>().apply {
        `when`(id).thenReturn(ObjectId("6611f74a927c68691c418626"))
        `when`(status).thenReturn(StatusEnum.CANCELED)
        `when`(inclusionDate).thenReturn(LocalDateTime.now())
        `when`(paymentDate).thenReturn(LocalDateTime.now())
        `when`(value).thenReturn(300.0)
        `when`(description).thenReturn("Canceled payment description")
        `when`(recurrence).thenReturn(recurrenceMock)
        `when`(destination).thenReturn(destinationMock)
    },
    mock<Payment>().apply {
        `when`(id).thenReturn(ObjectId("6611f74a927c68691c418627"))
        `when`(status).thenReturn(StatusEnum.CANCELED)
        `when`(inclusionDate).thenReturn(LocalDateTime.now())
        `when`(paymentDate).thenReturn(LocalDateTime.now())
        `when`(value).thenReturn(350.0)
        `when`(description).thenReturn("Another canceled payment description")
        `when`(recurrence).thenReturn(recurrenceMock)
        `when`(destination).thenReturn(destinationMock)
    }
)


val paymentsMock: Iterable<Payment> = listOf(
    mock(Payment::class.java).apply {
        `when`(id).thenReturn(ObjectId("6611f74a927c68691c418622"))
        `when`(status).thenReturn(StatusEnum.EFFECTED)
        `when`(inclusionDate).thenReturn(LocalDateTime.now())
        `when`(paymentDate).thenReturn(LocalDateTime.now())
        `when`(value).thenReturn(100.0)
        `when`(description).thenReturn("Payment description")
        `when`(recurrence).thenReturn(recurrenceMock)
        `when`(destination).thenReturn(destinationMock)
    },
    mock(Payment::class.java).apply {
        `when`(id).thenReturn(ObjectId("6611f74a927c68691c418623"))
        `when`(status).thenReturn(StatusEnum.EFFECTED)
        `when`(inclusionDate).thenReturn(LocalDateTime.now())
        `when`(paymentDate).thenReturn(LocalDateTime.now())
        `when`(value).thenReturn(200.0)
        `when`(description).thenReturn("Another payment description")
        `when`(recurrence).thenReturn(recurrenceMock)
        `when`(destination).thenReturn(destinationMock)
    }
)

val destinationMock: Destination = mock(Destination::class.java).apply {
    `when`(pixKey).thenReturn("gabrielcora20@gmail.com")
    `when`(keyType).thenReturn(PixKeyTypeEnum.EMAIL)
}

val recurrenceMock: Recurrence = mock(Recurrence::class.java).apply {
    `when`(endDate).thenReturn(LocalDateTime.now())
    `when`(frequencyType).thenReturn(FrequencyTypeEnum.MONTHLY)
}