package com.gabrielcora.presentation.util.exceptions.handlers

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class NotFoundExceptionHandler {
    @ExceptionHandler(NotFoundException::class)
    fun handleHttpMessageNotReadable(ex: NotFoundException): ResponseEntity<Any> {
        return ResponseEntity(ExceptionResponse("Não encontrado"), HttpStatus.NOT_FOUND)
    }
}