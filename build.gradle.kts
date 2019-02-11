import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	val kotlinVersion = "1.3.21"
	id("org.springframework.boot") version "2.1.2.RELEASE"
	id("org.jetbrains.kotlin.jvm") version kotlinVersion
	id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
	id("io.spring.dependency-management") version "1.0.6.RELEASE"
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
}


dependencies {
	compile("org.springframework.boot:spring-boot-starter-web")
	compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	compile("org.jetbrains.kotlin:kotlin-compiler")
	compile("org.jetbrains.kotlin:kotlin-script-util") {
		exclude(group = "com.jcabi", module = "jcabi-aether")
		exclude(group = "org.apache.maven", module = "maven-core")
		exclude(group = "org.sonatype.aether", module = "aether-api")
	}
	compile("com.fasterxml.jackson.module:jackson-module-kotlin")
	testCompile("org.springframework.boot:spring-boot-starter-test") {
		exclude(module = "junit")
	}
	testCompile("org.junit.jupiter:junit-jupiter-api")
	testRuntime("org.junit.jupiter:junit-jupiter-engine")
}
