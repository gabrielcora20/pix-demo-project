package com.gabrielcora.listener.configuration

import io.opentelemetry.api.OpenTelemetry
import io.opentelemetry.api.common.Attributes
import io.opentelemetry.context.propagation.ContextPropagators
import io.opentelemetry.exporter.otlp.logs.OtlpGrpcLogRecordExporter
import io.opentelemetry.instrumentation.logback.appender.v1_0.OpenTelemetryAppender
import io.opentelemetry.sdk.OpenTelemetrySdk
import io.opentelemetry.sdk.logs.LogRecordProcessor
import io.opentelemetry.sdk.logs.SdkLoggerProvider
import io.opentelemetry.sdk.logs.export.BatchLogRecordProcessor
import io.opentelemetry.sdk.resources.Resource
import io.opentelemetry.sdk.trace.SdkTracerProvider
import io.opentelemetry.semconv.ResourceAttributes
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
class OpenTelemetryConfig(@Value("\${otlp.grpc.endpoint}") val otlpGrpcEndpoint: String) {
    @Bean
    fun openTelemetry(
        sdkLoggerProvider: SdkLoggerProvider?,
        sdkTracerProvider: SdkTracerProvider?,
        contextPropagators: ContextPropagators?,
    ): OpenTelemetry {
        val openTelemetrySdk = OpenTelemetrySdk.builder()
            .setLoggerProvider(sdkLoggerProvider!!)
            .setTracerProvider(sdkTracerProvider!!)
            .setPropagators(contextPropagators!!)
            .build()
        OpenTelemetryAppender.install(openTelemetrySdk)
        return openTelemetrySdk
    }

    @Bean
    fun otelSdkLoggerProvider(
        environment: Environment,
        logRecordProcessors: ObjectProvider<LogRecordProcessor?>,
    ): SdkLoggerProvider {
        val applicationName = environment.getProperty("spring.application.name", "application")
        val springResource = Resource.create(Attributes.of(ResourceAttributes.SERVICE_NAME, applicationName))
        val builder = SdkLoggerProvider.builder()
            .setResource(Resource.getDefault().merge(springResource))
        logRecordProcessors.orderedStream().forEach { processor: LogRecordProcessor? ->
            builder.addLogRecordProcessor(
                processor!!
            )
        }
        return builder.build()
    }

    @Bean
    fun otelLogRecordProcessor(): LogRecordProcessor {
        return BatchLogRecordProcessor
            .builder(
                OtlpGrpcLogRecordExporter.builder()
                    .setEndpoint(otlpGrpcEndpoint)
                    .build()
            )
            .build()
    }
}