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
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.8.0")
	implementation("org.reactivestreams:reactive-streams:1.0.4")
	implementation("io.projectreactor:reactor-core:3.6.4")
	implementation("io.micrometer:micrometer-registry-prometheus:1.12.4")
	implementation("org.springframework.boot:spring-boot-starter-actuator:3.2.4")
	runtimeOnly("io.micrometer:micrometer-registry-otlp:1.12.4")
	implementation("io.opentelemetry:opentelemetry-api:1.31.0")
	implementation("io.micrometer:micrometer-tracing-bridge-otel:1.2.4")
	implementation("io.opentelemetry:opentelemetry-exporter-otlp:1.31.0")
	runtimeOnly("io.opentelemetry:opentelemetry-exporter-common:1.31.0")
	implementation("io.opentelemetry.instrumentation:opentelemetry-logback-appender-1.0:1.31.0-alpha")
	implementation("io.opentelemetry:opentelemetry-sdk:1.31.0")
	implementation("io.opentelemetry.instrumentation:opentelemetry-instrumentation-api-semconv:1.31.0-alpha")
	runtimeOnly("io.opentelemetry:opentelemetry-exporter-otlp-common:1.31.0")
	implementation("jakarta.validation:jakarta.validation-api:3.0.2")
	implementation("org.springframework.boot:spring-boot-starter-amqp:3.2.4")
	implementation("org.aspectj:aspectjweaver:1.9.22")
	implementation("org.springframework:spring-context:6.1.5")
	implementation(project(":pix"))
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.mockito:mockito-core:5.11.0")
	testImplementation("junit:junit:4.13.2")
	testImplementation("io.github.hakky54:logcaptor:2.9.2")
	testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
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
