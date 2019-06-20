package io.spring.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer
import org.springframework.web.servlet.view.script.ScriptTemplateViewResolver
import java.util.Locale
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.concurrent.ConcurrentHashMap
import javax.script.CompiledScript


@SpringBootApplication
class Application : WebMvcConfigurer {

    @Bean
    fun kotlinScriptConfigurer() = ScriptTemplateConfigurer().apply {
		engineName = "kotlin"
		setScripts("scripts/render.kts")
		renderFunction = "render"
		isSharedEngine = false
    }

    @Bean
    fun kotlinScriptViewResolver() = ScriptTemplateViewResolver().apply {
        setPrefix("templates/")
        setSuffix(".kts")
    }

    @Bean
    fun localeResolver() = SessionLocaleResolver().apply {
        setDefaultLocale(Locale.ENGLISH)
    }

    @Bean
    fun localeChangeInterceptor() = LocaleChangeInterceptor()

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(localeChangeInterceptor())
    }

	@Bean
	fun cache() = ConcurrentHashMap<String, CompiledScript>()

}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
