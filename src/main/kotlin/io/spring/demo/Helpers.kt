package io.spring.demo

import org.springframework.core.io.ClassPathResource
import org.springframework.util.FileCopyUtils
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import javax.script.ScriptEngineManager

fun <T> Iterable<T>.line(function: (foo: T) -> String): String { return joinToString(separator = "\n") { foo -> function.invoke(foo) } }

fun include(viewName: String) : String {
    // TODO Make prefix and suffix configurable, eventually find a way to reuse ViewResolver configuration
    val engine = ScriptEngineManager().getEngineByName("kotlin")
    val resource = ClassPathResource("templates/$viewName.kts")
    val reader = InputStreamReader(resource.inputStream, StandardCharsets.UTF_8)
    val template = FileCopyUtils.copyToString(reader)
    return engine.eval(template) as String
}
