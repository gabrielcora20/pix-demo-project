package com.gabrielcora.listener.configuration

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.support.converter.MessageConverter

import org.assertj.core.api.Assertions.assertThat
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter

@ExtendWith(MockitoExtension::class)
class RabbitMqConfigTest {

    @Mock
    private lateinit var connectionFactory: ConnectionFactory

    private val rabbitMqConfig = RabbitMqConfig()

    @Test
    fun `test RabbitTemplate bean configuration`() {
        val rabbitTemplate = rabbitMqConfig.rabbitTemplate(connectionFactory, rabbitMqConfig.messageConverter())
        assertThat(rabbitTemplate).isNotNull
        assertThat(rabbitTemplate.connectionFactory).isEqualTo(connectionFactory)
        assertThat(rabbitTemplate.messageConverter).isInstanceOf(MessageConverter::class.java)
    }

    @Test
    fun `test MessageConverter bean configuration`() {
        val messageConverter = rabbitMqConfig.messageConverter()
        assertThat(messageConverter).isNotNull
        assertThat(messageConverter).isInstanceOf(Jackson2JsonMessageConverter::class.java)
    }

    @Test
    fun `test DirectExchange bean configuration`() {
        val directExchange = rabbitMqConfig.directExchange()
        assertThat(directExchange).isNotNull
        assertThat(directExchange.name).isEqualTo("payment.changes")
    }

    @Test
    fun `test paymentRecurrenceChangedQueue bean configuration`() {
        val queue = rabbitMqConfig.paymentRecurrenceChangedQueue()
        assertThat(queue).isNotNull
        assertThat(queue.name).isEqualTo("processpaymentrecurrencechangeddo")
        assertThat(queue.isDurable).isEqualTo(true)
    }

    @Test
    fun `test paymentDeletedQueue bean configuration`() {
        val queue = rabbitMqConfig.paymentDeletedQueue()
        assertThat(queue).isNotNull
        assertThat(queue.name).isEqualTo("processpaymentdeleteddo")
        assertThat(queue.isDurable).isEqualTo(true)
    }

    @Test
    fun `test paymentUpdatedQueue bean configuration`() {
        val queue = rabbitMqConfig.paymentUpdatedQueue()
        assertThat(queue).isNotNull
        assertThat(queue.name).isEqualTo("processpaymentupdateddo")
        assertThat(queue.isDurable).isEqualTo(true)
    }

    @Test
    fun `test paymentRegisteredQueue bean configuration`() {
        val queue = rabbitMqConfig.paymentRegisteredQueue()
        assertThat(queue).isNotNull
        assertThat(queue.name).isEqualTo("processpaymentregistereddo")
        assertThat(queue.isDurable).isEqualTo(true)
    }

    @Test
    fun `test bindingPaymentRecurrenceChangedQueue bean configuration`() {
        val directExchange = rabbitMqConfig.directExchange()
        val paymentRecurrenceChangedQueue = rabbitMqConfig.paymentRecurrenceChangedQueue()
        val binding = rabbitMqConfig.bindingPaymentRecurrenceChangedQueue(directExchange, paymentRecurrenceChangedQueue)

        assertThat(binding).isNotNull
        assertThat(binding.destination).isEqualTo("processpaymentrecurrencechangeddo")
        assertThat(binding.routingKey).isEqualTo("payment-recurrence-updated")
    }

    @Test
    fun `test bindingPaymentDeletedQueue bean configuration`() {
        val directExchange = rabbitMqConfig.directExchange()
        val paymentDeletedQueue = rabbitMqConfig.paymentDeletedQueue()
        val binding = rabbitMqConfig.bindingPaymentDeletedQueue(directExchange, paymentDeletedQueue)

        assertThat(binding).isNotNull
        assertThat(binding.destination).isEqualTo("processpaymentdeleteddo")
        assertThat(binding.routingKey).isEqualTo("payment-deleted")
    }

    @Test
    fun `test bindingPaymentUpdatedQueue bean configuration`() {
        val directExchange = rabbitMqConfig.directExchange()
        val paymentUpdatedQueue = rabbitMqConfig.paymentUpdatedQueue()
        val binding = rabbitMqConfig.bindingPaymentUpdatedQueue(directExchange, paymentUpdatedQueue)

        assertThat(binding).isNotNull
        assertThat(binding.destination).isEqualTo("processpaymentupdateddo")
        assertThat(binding.routingKey).isEqualTo("payment-updated")
    }

    @Test
    fun `test bindingPaymentRegisteredQueue bean configuration`() {
        val directExchange = rabbitMqConfig.directExchange()
        val paymentRegisteredQueue = rabbitMqConfig.paymentRegisteredQueue()
        val binding = rabbitMqConfig.bindingPaymentRegisteredQueue(directExchange, paymentRegisteredQueue)

        assertThat(binding).isNotNull
        assertThat(binding.destination).isEqualTo("processpaymentregistereddo")
        assertThat(binding.routingKey).isEqualTo("payment-registered")
    }
}