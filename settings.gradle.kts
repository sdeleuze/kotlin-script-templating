pluginManagement {
	repositories {
		gradlePluginPortal()
		maven("https://dl.bintray.com/kotlin/kotlin-dev")
		maven("https://repo.spring.io/milestone")
	}
	resolutionStrategy {
		eachPlugin {
			if (requested.id.id == "org.springframework.boot") {
				useModule("org.springframework.boot:spring-boot-gradle-plugin:${requested.version}")
			}
			if (requested.id.id == "org.jetbrains.kotlin.jvm") {
				useModule("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:${requested.version}")
			}
		}
	}

}