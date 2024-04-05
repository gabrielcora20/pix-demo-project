package com.gabrielcora.pixcore.payment.domain.queries

import com.gabrielcora.cqrspackage.domain.queries.Query
import com.gabrielcora.pixcore.payment.domain.models.Payment
import com.gabrielcora.pixcore.payment.domain.models.enums.StatusEnum

class GetPaymentsByStatusQuery(
    val status: StatusEnum
): Query<Iterable<Payment>>