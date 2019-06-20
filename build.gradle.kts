import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	val kotlinVersion = "1.3.40"
	id("org.springframework.boot") version "2.2.0.M4"
	id("org.jetbrains.kotlin.jvm") version kotlinVersion
	id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		jvmTarget = "1.8"
		freeCompilerArgs = listOf("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

version = "0.0.1-SNAPSHOT"

tasks.withType<BootJar> {
	requiresUnpack("**/kotlin-compiler-*.jar")
}

repositories {
	mavenCentral()
	maven("https://repo.spring.io/milestone")
}

dependencies {
	compile("org.springframework.boot:spring-boot-starter-web")
	compile("io.projectreactor:reactor-core")
	compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	compile("org.jetbrains.kotlin:kotlin-script-runtime")
	runtime("org.jetbrains.kotlin:kotlin-scripting-jsr223-embeddable")
	compile("com.fasterxml.jackson.module:jackson-module-kotlin")
	testCompile("org.springframework.boot:spring-boot-starter-test")
}
