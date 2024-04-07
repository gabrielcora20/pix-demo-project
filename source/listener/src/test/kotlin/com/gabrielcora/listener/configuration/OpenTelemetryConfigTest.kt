package com.gabrielcora.listener.configuration

import io.opentelemetry.context.propagation.ContextPropagators
import io.opentelemetry.sdk.OpenTelemetrySdk
import io.opentelemetry.sdk.logs.SdkLoggerProvider
import io.opentelemetry.sdk.trace.SdkTracerProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class OpenTelemetryConfigTest {
    private val otlpGrpcEndpoint = "otlp_endpoint"

    @Test
    fun `test openTelemetry bean configuration`() {
        val openTelemetryConfig = OpenTelemetryConfig(otlpGrpcEndpoint)

        val openTelemetry = openTelemetryConfig.openTelemetry(
            mock<SdkLoggerProvider>(),
            mock<SdkTracerProvider>(),
            mock<ContextPropagators>()
        )

        assertThat(openTelemetry).isInstanceOf(OpenTelemetrySdk::class.java)
    }
}