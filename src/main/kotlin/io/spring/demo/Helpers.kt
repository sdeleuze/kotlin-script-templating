package io.spring.demo

import javax.script.ScriptEngineManager
import javax.script.SimpleBindings
import kotlin.script.templates.standard.ScriptTemplateWithBindings

fun ScriptTemplateWithBindings.include(path: String, model: Map<String, Any>? = null) :String {
    val engine = ScriptEngineManager().getEngineByName("kotlin")
    var includeBindings = if (model != null) {
        val b = SimpleBindings(LinkedHashMap(model))
        b["include"] = bindings["include"]
        b["i18n"] = bindings["i18n"]
        b
    } else {
        val b = SimpleBindings(bindings)
        b.remove("kotlin.script.history")
        b
    }
    val template = (bindings["include"] as (String) -> String).invoke(path)
    return engine.eval(template ,includeBindings) as String
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

var ScriptTemplateWithBindings.title: String
  get() = bindings["title"] as String
  set(value) { throw UnsupportedOperationException()}