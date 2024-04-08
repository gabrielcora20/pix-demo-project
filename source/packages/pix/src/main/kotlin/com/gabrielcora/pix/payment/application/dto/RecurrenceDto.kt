package com.gabrielcora.pix.payment.application.dto

import com.gabrielcora.pix.payment.domain.models.enums.FrequencyTypeEnum
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

@ApiModel(description = "Informações da recorrência do pagamento.")
data class RecurrenceDto(
    @field:ApiModelProperty(
        value = "A data de término da recorrência",
        example = "2024-04-30T23:59:59",
        dataType = "date-time",
        required = true
    )
    @field:NotNull(message = "A data de término da recorrência não pode ser nula")
    val endDate: LocalDateTime?,

    @field:ApiModelProperty(
        value = "O tipo de frequência da recorrência",
        example = "MONTHLY",
        required = true
    )
    @field:NotBlank(message = "A frequencia da recorrência não pode estar vazia")
    @field:NotNull(message = "A frequencia da recorrência não pode ser nula")
    val frequencyType: FrequencyTypeEnum?,
)