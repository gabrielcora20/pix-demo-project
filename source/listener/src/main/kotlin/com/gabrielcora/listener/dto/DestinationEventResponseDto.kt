package com.gabrielcora.listener.dto

import com.gabrielcora.pix.payment.domain.models.enums.PixKeyTypeEnum

data class DestinationEventResponseDto(
    val pixKey: String,
    val keyType: PixKeyTypeEnum,
)