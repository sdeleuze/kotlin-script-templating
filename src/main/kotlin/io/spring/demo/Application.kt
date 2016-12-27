package io.spring.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer
import org.springframework.web.servlet.view.script.ScriptTemplateViewResolver

@SpringBootApplication
open class Application {

    @Bean
    open fun kotlinScriptConfigurer(): ScriptTemplateConfigurer {
        val configurer = ScriptTemplateConfigurer()
        configurer.engineName = "kotlin"
        configurer.setScripts("scripts/render.kts")
        configurer.renderFunction = "render"
        return configurer
    }

    @Bean
    open fun txtViewResolver(): ViewResolver {
        val viewResolver = ScriptTemplateViewResolver()
        viewResolver.setPrefix("templates/")
        viewResolver.setSuffix(".ktt")
        return viewResolver
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
