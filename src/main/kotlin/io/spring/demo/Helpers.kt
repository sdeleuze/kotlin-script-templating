package io.spring.demo

import kotlinx.html.TagConsumer
import kotlinx.html.stream.createHTML
import java.util.concurrent.ConcurrentHashMap
import javax.script.Compilable
import javax.script.CompiledScript
import javax.script.ScriptEngineManager
import javax.script.SimpleBindings
import kotlin.script.templates.standard.ScriptTemplateWithBindings

class KotlinxTemplate private constructor(private val consumer: TagConsumer<String>,
                                          val scriptTemplate: ScriptTemplateWithBindings) : TagConsumer<String> by consumer {
    constructor(scriptTemplate: ScriptTemplateWithBindings) : this(createHTML(prettyPrint = false), scriptTemplate)

    fun include(path: String, model: Map<String, Any>? = null) {
        consumer.onTagContentUnsafe {
            +scriptTemplate.include(path, model)
        }
    }
}

fun ScriptTemplateWithBindings.template(html: KotlinxTemplate.() -> Unit): String {
    val template = KotlinxTemplate(this)
    template.html()
    return template.finalize()
}

fun ScriptTemplateWithBindings.include(path: String, model: Map<String, Any>? = null): String {
    val cache = bindings["cache"]!! as ConcurrentHashMap<String, CompiledScript>
    val includeBindings = if (model != null) {
        val b = SimpleBindings(LinkedHashMap(model))
        b["include"] = bindings["include"]
        b["i18n"] = bindings["i18n"]
		b["cache"] = cache
		b
    } else {
        val b = SimpleBindings(bindings)
        b.remove("kotlin.script.state")
        b
    }
    val template = (bindings["include"] as (String) -> String).invoke(path)
	val compiledScript = cache.getOrPut(path, { compilableEngine().compile(template) })
    return compiledScript.eval(includeBindings) as String
}

fun ScriptTemplateWithBindings.i18n(code: String) =
    (bindings["i18n"] as (String) -> String).invoke(code)

fun <T> Iterable<T>.joinToLine(function: (foo: T) -> String): String
  { return joinToString(separator = "\n") { foo -> function.invoke(foo) } }

var ScriptTemplateWithBindings.users: List<User>
  get() = bindings["users"] as List<User>
  set(value) { throw UnsupportedOperationException()}

var ScriptTemplateWithBindings.user: User
  get() = bindings["user"] as User
  set(value) { throw UnsupportedOperationException()}

fun compilableEngine() = ScriptEngineManager().getEngineByName("kotlin") as Compilable
