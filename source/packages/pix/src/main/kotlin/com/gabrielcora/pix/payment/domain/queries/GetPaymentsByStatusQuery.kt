package com.gabrielcora.pix.payment.domain.queries

import com.gabrielcora.pix.payment.domain.models.Payment
import com.gabrielcora.pix.payment.domain.models.enums.StatusEnum

class GetPaymentsByStatusQuery(
    val status: StatusEnum,
) : Query<Iterable<Payment>>