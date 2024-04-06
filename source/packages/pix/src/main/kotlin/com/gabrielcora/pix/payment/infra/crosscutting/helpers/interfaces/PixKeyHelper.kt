package com.gabrielcora.pix.payment.infra.crosscutting.helpers.interfaces

import com.gabrielcora.pix.payment.domain.models.enums.PixKeyTypeEnum

interface PixKeyHelper {
    fun getTypeFromPixKey(key: String): PixKeyTypeEnum
}