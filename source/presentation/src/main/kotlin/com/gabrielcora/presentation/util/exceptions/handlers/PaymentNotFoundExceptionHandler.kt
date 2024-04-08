package com.gabrielcora.presentation.util.exceptions.handlers

import com.gabrielcora.pix.payment.domain.exceptions.PaymentNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class PaymentNotFoundExceptionHandler {
    @ExceptionHandler(PaymentNotFoundException::class)
    fun handleHttpMessageNotReadable(ex: PaymentNotFoundException): ResponseEntity<Any> {
        return ResponseEntity(ExceptionResponse(ex.message.toString()), HttpStatus.NOT_FOUND)
    }
}