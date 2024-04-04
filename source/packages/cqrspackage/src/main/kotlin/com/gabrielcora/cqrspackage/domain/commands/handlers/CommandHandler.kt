package com.gabrielcora.cqrspackage.domain.commands.handlers

import com.gabrielcora.cqrspackage.domain.commands.Command
import com.gabrielcora.cqrspackage.infra.data.interfaces.IUnitOfWork

abstract class CommandHandler<R, C : Command<R>?> {
    abstract suspend fun handle(command: C): R

    suspend fun commit(unitOfWork: IUnitOfWork): Boolean{
        return unitOfWork.commit()
    }
}