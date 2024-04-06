import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
}

group = "com.gabrielcora"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.8.0")
	implementation("org.reactivestreams:reactive-streams:1.0.4")
	implementation("io.projectreactor:reactor-core:3.6.4")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-actuator
	implementation("org.springframework.boot:spring-boot-starter-actuator:3.2.4")
	// https://mvnrepository.com/artifact/io.micrometer/micrometer-registry-otlp
	runtimeOnly("io.micrometer:micrometer-registry-otlp:1.12.4")
	implementation("io.opentelemetry:opentelemetry-api:1.31.0")
	// https://mvnrepository.com/artifact/io.micrometer/micrometer-tracing-bridge-otel
	implementation("io.micrometer:micrometer-tracing-bridge-otel:1.2.4")
	implementation("io.opentelemetry:opentelemetry-exporter-otlp:1.31.0")
	// https://mvnrepository.com/artifact/io.opentelemetry/opentelemetry-exporter-common
	runtimeOnly("io.opentelemetry:opentelemetry-exporter-common:1.31.0")
	// https://mvnrepository.com/artifact/io.opentelemetry.instrumentation/opentelemetry-logback-appender-1.0
	implementation("io.opentelemetry.instrumentation:opentelemetry-logback-appender-1.0:1.31.0-alpha")
	implementation("io.opentelemetry:opentelemetry-sdk:1.31.0")
	// https://mvnrepository.com/artifact/io.opentelemetry.instrumentation/opentelemetry-instrumentation-api-semconv
	implementation("io.opentelemetry.instrumentation:opentelemetry-instrumentation-api-semconv:1.31.0-alpha")
	// https://mvnrepository.com/artifact/io.opentelemetry/opentelemetry-exporter-otlp-common
	runtimeOnly("io.opentelemetry:opentelemetry-exporter-otlp-common:1.31.0")
	implementation(project(":pix"))
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "21"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
