package com.gabrielcora.listener.eventlisteners

import com.gabrielcora.listener.dto.PaymentEventResponseDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.amqp.rabbit.annotation.RabbitListener

@Component
class PaymentChangedEventListener {
    private val logger: Logger = LoggerFactory.getLogger(PaymentChangedEventListener::class.java)
    @RabbitListener(queues = ["processpaymentrecurrencechangeddo"])
    fun handlePaymentRecurrenceChanged(message: PaymentEventResponseDto) {
        logger.info("executing handlePaymentRecurrenceChanged")
    }

    @RabbitListener(queues = ["processpaymentdeleteddo"])
    fun handlePaymentDeleted(message: PaymentEventResponseDto) {
        logger.info("executing handlePaymentDeleted")
    }

    @RabbitListener(queues = ["processpaymentupdateddo"])
    fun handlePaymentUpdated(message: PaymentEventResponseDto) {
        logger.info("executing handlePaymentUpdated")
    }

    @RabbitListener(queues = ["processpaymentregistereddo"])
    fun handlePaymentRegistered(message: PaymentEventResponseDto) {
        logger.info("executing handlePaymentRegistered")
    }
}