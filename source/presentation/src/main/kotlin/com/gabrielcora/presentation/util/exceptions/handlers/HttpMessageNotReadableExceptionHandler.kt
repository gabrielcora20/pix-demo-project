package com.gabrielcora.presentation.util.exceptions.handlers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class HttpMessageNotReadableExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException): ResponseEntity<Any> {
        return ResponseEntity(ExceptionResponse("Não foi possivel serializar o json recebido. Por favor, dertifique-se de enviar valores não nulos todos os campos obrigatórios."), HttpStatus.BAD_REQUEST)
    }
}