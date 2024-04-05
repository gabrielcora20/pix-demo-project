package com.gabrielcora.cqrspackage.domain.queries.handlers

import com.gabrielcora.cqrspackage.domain.queries.Query

abstract class QueryHandler<R, Q : Query<R>?> {
    abstract suspend fun handle(query: Q): R
}