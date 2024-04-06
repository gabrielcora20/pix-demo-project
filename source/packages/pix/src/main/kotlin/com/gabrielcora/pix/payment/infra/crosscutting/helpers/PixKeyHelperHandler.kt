package com.gabrielcora.pix.payment.infra.crosscutting.helpers

import com.gabrielcora.pix.payment.domain.models.enums.PixKeyTypeEnum
import com.gabrielcora.pix.payment.infra.crosscutting.helpers.interfaces.PixKeyHelper
import org.springframework.stereotype.Component

@Component
class PixKeyHelperHandler : PixKeyHelper {
    override fun getTypeFromPixKey(key: String): PixKeyTypeEnum {
        return when {
            key.matches(Regex("^[0-9]{11}$")) -> PixKeyTypeEnum.CPF
            key.matches(Regex("^\\+[1-9][0-9]\\d{1,14}$")) -> PixKeyTypeEnum.PHONE
            key.matches(Regex("^[a-z0-9.!#$&'*+/=?^_`{}~-]+@[a-z0-9]+(?:\\.[a-z0-9]+)*$")) -> PixKeyTypeEnum.EMAIL
            key.matches(Regex("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")) -> PixKeyTypeEnum.RANDOM
            else -> throw IllegalArgumentException("A chave pix fornecida é inválida")
        }
    }
}