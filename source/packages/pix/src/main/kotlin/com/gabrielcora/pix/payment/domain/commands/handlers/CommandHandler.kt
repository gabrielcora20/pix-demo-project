package com.gabrielcora.pix.payment.domain.commands.handlers

import com.gabrielcora.pix.payment.domain.commands.Command
import com.gabrielcora.pix.payment.domain.interfaces.UnitOfWork
import org.apache.coyote.BadRequestException

interface CommandHandler<R, C : Command<R>?> {
    suspend fun handle(command: C): R

    suspend fun commit(unitOfWork: UnitOfWork): Boolean {
        if (!unitOfWork.commit())
            throw BadRequestException("Erro ao executar uma ou mais operações no servidor")

        return true
    }
}