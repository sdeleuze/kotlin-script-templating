import javax.script.Invocable
import javax.script.ScriptEngineManager

fun render(template: String, model: Map<String, Any>, url: String): String {
    val engine = ScriptEngineManager().getEngineByName("kotlin")
    engine.eval(template)
    // TODO Use engine.eval(String, Bindings) when it will work
    return (engine as Invocable).invokeFunction("template", model) as String
}