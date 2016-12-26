
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.SimpleBindings

fun render(template: String, model: Map<String, Object>, url: String): String {
    val engine: ScriptEngine = ScriptEngineManager().getEngineByName("kotlin")
    val bindings = SimpleBindings()
    for ((key, value) in model) {
        bindings.put(key, value)
        println("$key : $value")
    }
    bindings.put("url", url)
    return engine.eval(template, bindings) as String
}