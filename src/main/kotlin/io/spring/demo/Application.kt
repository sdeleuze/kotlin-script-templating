package io.spring.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer
import org.springframework.web.servlet.view.script.ScriptTemplateViewResolver
import java.util.Locale
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


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

}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
