package com.gabrielcora.pix.payment.infra.crosscutting.helpers

import com.gabrielcora.pix.payment.domain.models.anotations.ShouldNotBePatched
import com.gabrielcora.pix.payment.infra.crosscutting.helpers.interfaces.ObjectMergeHelper
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Component

@Component
class ObjectMergeHelperHandler: ObjectMergeHelper {
    override fun <T> merge(source: T, updates: Map<String, Any?>): T {
        for ((key, value) in updates) {
            if (BeanUtils.getPropertyDescriptor(source!!::class.java, key) != null) {
                try {
                    val field = source!!::class.java.getDeclaredField(key)
                    field.isAccessible = true

                    if(shouldNotBePatched(source!!::class.java, key))
                        throw IllegalArgumentException("Não é permitido realizar uma alteração no campo '$key'")

                    if (value != null && field.type != value.javaClass)
                        throw IllegalArgumentException("O tipo do valor enviado no campo '$key' está incorreto")

                    field.set(source, value)
                } catch (_: NoSuchFieldException) {}
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