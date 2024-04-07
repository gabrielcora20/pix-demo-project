package com.gabrielcora.pix.payment.infra.crosscutting.helpers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.gabrielcora.pix.payment.domain.models.anotations.ShouldNotBePatched
import com.gabrielcora.pix.payment.infra.crosscutting.helpers.interfaces.ObjectMergeHelper
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Component
import kotlin.reflect.full.cast
import kotlin.reflect.full.createInstance

@Component
class ObjectMergeHelperHandler : ObjectMergeHelper {
    override fun <T> merge(source: T, updates: Map<String, Any?>): T {
        for ((key, value) in updates) {
            if (BeanUtils.getPropertyDescriptor(source!!::class.java, key) == null)
                throw NoSuchFieldException("O campo '$key' não existe")

                val field = source!!::class.java.getDeclaredField(key)
                field.isAccessible = true

                if (shouldNotBePatched(source!!::class.java, key))
                    throw IllegalArgumentException("Não é permitido realizar uma alteração no campo '$key'")

                try {
                    val castedValue = ObjectMapper().apply {
                        registerModule(JavaTimeModule())
                    }.convertValue(value, field.type)

                    field.set(source, castedValue)
                } catch (ex: TypeCastException) {
                    throw TypeCastException("O tipo do valor enviado no campo '$key' não pôde ser convertido")
                }
        }
        return source
    }
    private fun shouldNotBePatched(clazz: Class<*>, fieldName: String): Boolean {
        val field = clazz.getDeclaredField(fieldName)
        val annotation = field.getAnnotation(ShouldNotBePatched::class.java)
        return annotation != null
    }
}