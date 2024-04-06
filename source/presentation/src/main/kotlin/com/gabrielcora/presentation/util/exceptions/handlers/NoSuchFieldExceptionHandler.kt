package com.gabrielcora.presentation.util.exceptions.handlers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class NoSuchFieldExceptionHandler {
    @ExceptionHandler(NoSuchFieldException::class)
    fun handleNoSuchField(ex: NoSuchFieldException): ResponseEntity<Any> {
        return ResponseEntity(ExceptionResponse(ex.message.toString()), HttpStatus.BAD_REQUEST)
    }
}