package com.gabrielcora.pix.payment.application.dto

import com.gabrielcora.pix.payment.domain.models.enums.PixKeyTypeEnum

data class DestinationDTO(
    val pixKey: String,
    val keyType: PixKeyTypeEnum,
)