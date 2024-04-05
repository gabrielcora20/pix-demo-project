package com.gabrielcora.pix.payment.domain.commands.handlers

import com.gabrielcora.pix.payment.domain.commands.Command
import com.gabrielcora.pix.payment.infra.data.interfaces.IUnitOfWork

abstract class CommandHandler<R, C : Command<R>?> {
    abstract suspend fun handle(command: C): R

    suspend fun commit(unitOfWork: IUnitOfWork): Boolean{
        return unitOfWork.commit()
    }
}