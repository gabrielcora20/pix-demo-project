package com.gabrielcora.pix.payment.domain.commands.handlers

import com.gabrielcora.pix.payment.domain.commands.Command
import com.gabrielcora.pix.payment.domain.interfaces.IUnitOfWork

abstract class CommandHandler<R, C : Command<R>?> {
    abstract suspend fun handle(command: C): R

    suspend fun commit(unitOfWork: IUnitOfWork): Boolean {
        if (!unitOfWork.commit())
            throw Exception("Erro ao executar uma ou mais operações no servidor")

        return true
    }
}