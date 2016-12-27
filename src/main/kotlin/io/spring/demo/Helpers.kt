package io.spring.demo

import org.springframework.core.io.ClassPathResource
import org.springframework.util.FileCopyUtils
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import javax.script.ScriptContext
import javax.script.ScriptEngineManager
import javax.script.SimpleBindings

fun <T> Iterable<T>.joinToLine(function: (foo: T) -> String): String { return joinToString(separator = "\n") { foo -> function.invoke(foo) } }

fun include(viewName: String, model: Map<String, Any?>? = null) : String {
    // TODO Make prefix and suffix configurable, eventually find a way to reuse ViewResolver configuration
    val engine = ScriptEngineManager().getEngineByName("kotlin")
    val bindings = SimpleBindings()
    if (model != null) bindings.putAll(model)
    engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE)
    val resource = ClassPathResource("templates/$viewName.kts")
    val reader = InputStreamReader(resource.inputStream, StandardCharsets.UTF_8)
    val template = FileCopyUtils.copyToString(reader)
    return engine.eval(template) as String
}
