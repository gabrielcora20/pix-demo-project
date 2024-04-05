package com.gabrielcora.pix.payment.infra.data.context

import com.gabrielcora.pix.payment.domain.models.DomainEntity

class DomainCommand(
    val command: suspend () -> Unit,
    val entity: DomainEntity
)
