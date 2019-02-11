package io.spring.demo

import java.util.concurrent.ConcurrentHashMap
import javax.script.Compilable
import javax.script.CompiledScript
import javax.script.ScriptEngineManager
import javax.script.SimpleBindings
import kotlin.script.templates.standard.ScriptTemplateWithBindings

fun ScriptTemplateWithBindings.include(path: String, model: Map<String, Any>? = null) :String {
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
	val compiledScript = cache.getOrPut(path) { compilableEngine().compile(template) }
    return compiledScript.eval(includeBindings) as String
}

fun ScriptTemplateWithBindings.i18n(code: String) =
    (bindings["i18n"] as (String) -> String).invoke(code)

fun <T> Iterable<T>.joinToLine(function: (foo: T) -> String): String
  { return joinToString(separator = "\n") { foo -> function.invoke(foo) } }

var ScriptTemplateWithBindings.users: List<User>
  get() = bindings["users"] as List<User>
  set(_) { throw UnsupportedOperationException()}

var ScriptTemplateWithBindings.user: User
  get() = bindings["user"] as User
  set(_) { throw UnsupportedOperationException()}

var ScriptTemplateWithBindings.title: String
  get() = bindings["title"] as String
  set(_) { throw UnsupportedOperationException()}

fun compilableEngine() = ScriptEngineManager().getEngineByName("kotlin") as Compilable