package com.gabrielcora.pixcore.payment.domain.queries

import com.gabrielcora.cqrspackage.domain.queries.Query
import com.gabrielcora.pixcore.payment.domain.models.Payment

class GetAllPaymentsQuery: Query<Iterable<Payment>>