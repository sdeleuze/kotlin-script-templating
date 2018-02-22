package io.spring.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
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
    fun kotlinScriptConfigurer(): ScriptTemplateConfigurer {
        val configurer = ScriptTemplateConfigurer()
        configurer.engineName = "kotlin"
        configurer.setScripts("scripts/render.kts")
        configurer.renderFunction = "render"
        configurer.isSharedEngine = false
        return configurer
    }

    @Bean
    @Profile("simple")
    fun templatePrefix() = "templates/"

    @Bean("templatePrefix")
    @Profile("kotlinx")
    fun kotlinxTemplatePrefix() = "kotlinx/templates/"

    @Bean
    fun kotlinScriptViewResolver(templatePrefix: String) = ScriptTemplateViewResolver().apply {
        setPrefix(templatePrefix)
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
    SpringApplication.run(Application::class.java, *args)
}
