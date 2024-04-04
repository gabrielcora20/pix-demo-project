package com.gabrielcora.cqrspackage.infra.data.context

import com.gabrielcora.cqrspackage.domain.models.DomainEntity

class DomainCommand(
    val command: suspend () -> Unit,
    val entity: DomainEntity
)
