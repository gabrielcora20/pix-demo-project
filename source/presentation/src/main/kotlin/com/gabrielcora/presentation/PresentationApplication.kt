package com.gabrielcora.presentation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import io.opentelemetry.api.GlobalOpenTelemetry
import io.opentelemetry.sdk.OpenTelemetrySdk
import io.opentelemetry.sdk.trace.SdkTracerProvider
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor

@SpringBootApplication
@ComponentScan("com.gabrielcora")
class PresentationApplication

fun main(args: Array<String>) {
    //val exporter = JaegerGrpcSpanExporter.builder().build()
    //val spanProcessor = SimpleSpanProcessor.create(exporter)
    //val tracerProvider = SdkTracerProvider.builder().addSpanProcessor(spanProcessor).build()
//
    //GlobalOpenTelemetry.set(
    //    OpenTelemetrySdk.builder()
    //        .setTracerProvider(tracerProvider)
    //        .build()
    //)

    runApplication<PresentationApplication>(*args)
}
