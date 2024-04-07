package com.gabrielcora.listener.configuration

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMqConfig {
    private val exchangeName = "payment.changes"

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory, messageConverter: MessageConverter): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = messageConverter
        return rabbitTemplate
    }

    @Bean
    fun messageConverter(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }

    @Bean
    fun directExchange(): DirectExchange {
        return DirectExchange(exchangeName)
    }

    @Bean
    fun paymentRecurrenceChangedQueue(): Queue {
        return Queue("processpaymentrecurrencechangeddo",true)
    }

    @Bean
    fun paymentDeletedQueue(): Queue {
        return Queue("processpaymentdeleteddo",true)
    }

    @Bean
    fun paymentUpdatedQueue(): Queue {
        return Queue("processpaymentupdateddo",true)
    }

    @Bean
    fun paymentRegisteredQueue(): Queue {
        return Queue("processpaymentregistereddo",true)
    }

    @Bean
    fun bindingPaymentRecurrenceChangedQueue(directExchange: DirectExchange, paymentRecurrenceChangedQueue: Queue): Binding {
        return BindingBuilder.bind(paymentRecurrenceChangedQueue)
            .to(directExchange)
            .with("payment-recurrence-updated")
    }

    @Bean
    fun bindingPaymentDeletedQueue(directExchange: DirectExchange, paymentDeletedQueue: Queue): Binding {
        return BindingBuilder.bind(paymentDeletedQueue)
            .to(directExchange)
            .with("payment-deleted")
    }

    @Bean
    fun bindingPaymentUpdatedQueue(directExchange: DirectExchange, paymentUpdatedQueue: Queue): Binding {
        return BindingBuilder.bind(paymentUpdatedQueue)
            .to(directExchange)
            .with("payment-updated")
    }

    @Bean
    fun bindingPaymentRegisteredQueue(directExchange: DirectExchange, paymentRegisteredQueue: Queue): Binding {
        return BindingBuilder.bind(paymentRegisteredQueue)
            .to(directExchange)
            .with("payment-registered")
    }
}