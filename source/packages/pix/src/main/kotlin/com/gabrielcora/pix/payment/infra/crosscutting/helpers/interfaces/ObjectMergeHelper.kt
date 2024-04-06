package com.gabrielcora.pix.payment.infra.crosscutting.helpers.interfaces

interface ObjectMergeHelper {
    fun <T> merge(source: T, updates: Map<String, Any?>): T
}