package com.gabrielcora.pix.payment.domain.queries.handlers

import com.gabrielcora.pix.payment.domain.queries.Query

abstract class QueryHandler<R, Q : Query<R>?> {
    abstract suspend fun handle(query: Q): R
}