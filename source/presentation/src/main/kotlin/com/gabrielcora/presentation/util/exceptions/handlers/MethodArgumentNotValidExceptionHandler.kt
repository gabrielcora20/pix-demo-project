package com.gabrielcora.presentation.util.exceptions.handlers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class MethodArgumentNotValidExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException): ResponseEntity<Any> {
        val errors = mutableMapOf<String, String>()
        ex.bindingResult.fieldErrors.forEach { fieldError ->
            errors[fieldError.field] = fieldError.defaultMessage ?: "Valor inválido"
        }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }
}
